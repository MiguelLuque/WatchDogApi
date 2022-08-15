package com.maik.WatchDogApi.domain.services.interfaces;

import com.maik.WatchDogApi.domain.entities.Role;
import com.maik.WatchDogApi.models.dto.AuthRequest;
import com.maik.WatchDogApi.models.dto.AuthResponse;

public interface AuthService {

    AuthResponse login(AuthRequest authRequest);

    AuthResponse registration(AuthRequest authRequest);

    Role findRoleByName(String name);
}
