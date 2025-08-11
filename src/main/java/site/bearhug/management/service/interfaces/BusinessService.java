package site.bearhug.management.service.interfaces;

import site.bearhug.management.presentation.dto.Response;
import site.bearhug.management.presentation.dto.request.BusinessRequest;
import site.bearhug.management.presentation.dto.request.BusinessResponse;

import java.util.List;

public interface BusinessService {

    Response<Void> create(BusinessRequest request, String userId);

    void delete(String id);

    Response<List<BusinessResponse>> findAllBusinessByUsername(String username);
}
