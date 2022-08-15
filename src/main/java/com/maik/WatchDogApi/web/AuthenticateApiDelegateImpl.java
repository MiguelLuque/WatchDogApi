package com.maik.WatchDogApi.web;

import com.maik.WatchDogApi.api.AuthApiDelegate;
import com.maik.WatchDogApi.domain.services.interfaces.AuthService;
import com.maik.WatchDogApi.models.dto.AuthRequest;
import com.maik.WatchDogApi.models.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticateApiDelegateImpl implements AuthApiDelegate {

    private final AuthService authService;

    @Override
    public ResponseEntity<AuthResponse> login(AuthRequest authRequest) {
        return ResponseEntity.ok().body(authService.login(authRequest));
    }

    @Override
    public ResponseEntity<AuthResponse> registration(AuthRequest authRequest) {
        return ResponseEntity.ok().body(authService.registration(authRequest));
    }
}
