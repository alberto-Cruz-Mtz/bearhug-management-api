package site.bearhug.management.service.interfaces;

import site.bearhug.management.presentation.dto.Response;
import site.bearhug.management.presentation.dto.request.AuthLoginRequest;
import site.bearhug.management.presentation.dto.request.AuthRegisterRequest;

public interface AuthenticationService {

    Response<String> login(AuthLoginRequest request);

    Response<String> register(AuthRegisterRequest request);

    void verifyUser(String username);
}
