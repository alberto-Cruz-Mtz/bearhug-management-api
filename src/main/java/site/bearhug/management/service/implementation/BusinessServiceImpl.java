package site.bearhug.management.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.bearhug.management.persistence.entity.BusinessEntity;
import site.bearhug.management.persistence.entity.user.UserEntity;
import site.bearhug.management.persistence.repository.BusinessRepository;
import site.bearhug.management.persistence.repository.user.UserRepository;
import site.bearhug.management.presentation.dto.Response;
import site.bearhug.management.presentation.dto.model.Status;
import site.bearhug.management.presentation.dto.request.BusinessRequest;
import site.bearhug.management.presentation.dto.request.BusinessResponse;
import site.bearhug.management.service.exception.ResourceNotFoundException;
import site.bearhug.management.service.interfaces.BusinessService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessService {

    private final BusinessRepository repository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Response<Void> create(BusinessRequest request, String username) {
        if (repository.existsByBusinessName(request.name()))
            throw new IllegalArgumentException("Business name already exists!");

        UserEntity user = userRepository.getReferenceByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        BusinessEntity entity = BusinessEntity.create(request.name(), request.description(), user);

        repository.save(entity);
        return new Response<>(null, Status.SUCCESS, "negocio creado correctamente", null);
    }

    @Transactional
    @Override
    public void delete(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }

        throw new ResourceNotFoundException("Business not found!");
    }

    @Override
    @Transactional(readOnly = true)
    public Response<List<BusinessResponse>> findAllBusinessByUsername(String username) {
        List<BusinessResponse> businesses = repository.findAllBusinessByUsername(username).stream().map(BusinessResponse::of).toList();
        return new Response<>(businesses, Status.SUCCESS, "businesses found successfully", null);
    }
}