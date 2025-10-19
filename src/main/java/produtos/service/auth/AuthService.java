package produtos.service.auth;

import produtos.dto.auth.LoginRequest;
import produtos.dto.auth.LoginResponse;
import produtos.dto.auth.RegisterRequest;

public interface AuthService {
    LoginResponse login(final LoginRequest request);
    LoginResponse register(final RegisterRequest request);
}

