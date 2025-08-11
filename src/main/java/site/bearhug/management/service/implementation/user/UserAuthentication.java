package site.bearhug.management.service.implementation.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import site.bearhug.management.persistence.entity.user.RoleEntity;
import site.bearhug.management.persistence.entity.user.UserEntity;
import site.bearhug.management.presentation.dto.Response;
import site.bearhug.management.presentation.dto.request.AuthLoginRequest;
import site.bearhug.management.presentation.dto.request.AuthRegisterRequest;
import site.bearhug.management.service.interfaces.AuthenticationService;
import site.bearhug.management.util.jwt.JwtUtil;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserAuthentication implements AuthenticationService {

    private final UserDetailsServiceImpl service;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public Response<String> login(AuthLoginRequest request) {
        String username = request.username();
        String password = request.password();

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = this.jwtUtil.generateToken(authentication);
        return new Response<>(token, "Success", "user authenticated successfully", null);
    }

    @Override
    public Response<String> register(AuthRegisterRequest request) {
        String username = request.username();
        String password = request.password();
        Set<RoleEntity> roles = this.service.getRolesByNameIn(request.roles());

        if (roles.isEmpty()) {
            throw new IllegalArgumentException("The roles specified does not exist.");
        }

        UserEntity userEntity = UserEntity.create(username, passwordEncoder.encode(password), roles);
        UserEntity userSaved = this.service.saveUser(userEntity);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userSaved.getUsername(), null, userSaved.getAuthorities());
        String token = this.jwtUtil.generateToken(authentication);

        return new Response<>(token, "Success", "user registered successfully", null);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails user = service.loadUserByUsername(username);

        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("username or password incorrect");
        }

        return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
    }

}
