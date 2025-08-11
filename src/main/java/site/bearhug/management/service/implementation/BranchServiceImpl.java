package site.bearhug.management.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.bearhug.management.persistence.entity.BranchEntity;
import site.bearhug.management.persistence.entity.BusinessEntity;
import site.bearhug.management.persistence.repository.BranchRepository;
import site.bearhug.management.persistence.repository.BusinessRepository;
import site.bearhug.management.presentation.dto.BranchRequest;
import site.bearhug.management.presentation.dto.Response;
import site.bearhug.management.service.exception.ResourceNotFoundException;
import site.bearhug.management.service.interfaces.BranchService;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository repository;
    private final BusinessRepository businessRepository;

    @Override
    @Transactional()
    public Response<BranchRequest> create(BranchRequest request, String businessId) {
        BusinessEntity business = businessRepository.findById(businessId).orElseThrow(() -> new ResourceNotFoundException("Business not found!"));

        BranchEntity branch = new BranchEntity(request.name(), request.address(), request.description(), business);
        BranchEntity savedBranch = repository.save(branch);

        return new Response<>(BranchRequest.of(savedBranch), "Success", "branch created successfully", null);
    }

    @Transactional(readOnly = true)
    public BranchEntity getBranch(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Branch not found!"));
    }

    @Override
    @Transactional(readOnly = true)
    public Response<BranchRequest> findBranchById(Long id) {
        return new Response<>(BranchRequest.of(getBranch(id)), "Success", "branch updated successfully", null);
    }

    @Override
    @Transactional()
    public void delete(Long id) {
        BranchEntity branch = getBranch(id);
        repository.delete(branch);
    }

}
