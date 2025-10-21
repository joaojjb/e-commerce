package produtos.service.auth;

import produtos.model.dto.auth.LoginRequest;
import produtos.model.dto.auth.LoginResponse;
import produtos.model.dto.auth.RegisterRequest;
import produtos.model.entity.User;

public interface AuthService {
    LoginResponse login(final LoginRequest request);
    LoginResponse register(final RegisterRequest request);
    User getUsuarioAtual();
}

