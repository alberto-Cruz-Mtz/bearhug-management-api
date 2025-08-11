package site.bearhug.management.service.interfaces;

import site.bearhug.management.presentation.dto.BranchRequest;
import site.bearhug.management.presentation.dto.Response;

public interface BranchService {

    Response<BranchRequest> create(BranchRequest request, String businessId);

    Response<BranchRequest> findBranchById(Long id);

    void delete(Long id);
}
