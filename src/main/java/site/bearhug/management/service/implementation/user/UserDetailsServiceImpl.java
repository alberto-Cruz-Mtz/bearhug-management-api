package site.bearhug.management.service.implementation.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.bearhug.management.persistence.entity.user.RoleEntity;
import site.bearhug.management.persistence.entity.user.UserEntity;
import site.bearhug.management.persistence.repository.user.RoleRepository;
import site.bearhug.management.persistence.repository.user.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(""));
        return this.create(user);
    }

    @Transactional()
    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Set<RoleEntity> getRolesByNameIn(List<String> roleNames) {
        return new HashSet<>(roleRepository.findRoleEntitiesByNameIn(roleNames));
    }

    @Transactional(readOnly = true)
    public UserEntity getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    private User create(UserEntity entity) {
        return new User(
                entity.getUsername(),
                entity.getPassword(),
                entity.isEnabled(),
                entity.isAccountNonExpired(),
                entity.isCredentialsNonExpired(),
                entity.isAccountNonLocked(),
                entity.getAuthorities());
    }
}
