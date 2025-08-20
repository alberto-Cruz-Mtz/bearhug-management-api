package site.bearhug.management.service.implementation.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.bearhug.management.persistence.entity.user.ProfileEntity;
import site.bearhug.management.persistence.entity.user.UserEntity;
import site.bearhug.management.persistence.repository.user.ProfileRepository;
import site.bearhug.management.presentation.dto.ProfileResponse;
import site.bearhug.management.presentation.dto.Response;
import site.bearhug.management.presentation.dto.model.Status;
import site.bearhug.management.presentation.dto.request.ProfileRequest;
import site.bearhug.management.util.SupabaseStorage;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl {

    private final ProfileRepository repository;
    private final SupabaseStorage storage;
    private final UserDetailsServiceImpl userDetailsService;

    public Response<ProfileResponse> create(ProfileRequest request, String username) throws IOException {
        UserEntity user = userDetailsService.getByUsername(username);
        String url = null;
        if (request.file() != null && !request.file().isEmpty()) {
            url = storage.uploadFile(request.file(), user.getId());
        } else {
            url = "#";
        }

        ProfileEntity entity = ProfileEntity.of(request, url);
        entity.setUser(user);
        ProfileEntity saved = repository.save(entity);

        return new Response<>(ProfileResponse.of(saved), Status.SUCCESS, "Perfil creado correctamente", null);
    }
}
