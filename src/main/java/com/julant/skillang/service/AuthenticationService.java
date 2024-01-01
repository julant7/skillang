package com.julant.skillang.service;

import com.julant.skillang.dto.JwtAuthenticationResponse;
import com.julant.skillang.dto.RefreshTokenRequest;
import com.julant.skillang.dto.SignInRequest;
import com.julant.skillang.dto.SignUpRequest;
import com.julant.skillang.model.User;

public interface AuthenticationService {
    User signup(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signin(SignInRequest signInRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
