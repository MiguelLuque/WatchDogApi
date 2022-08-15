package com.maik.WatchDogApi.domain.services.interfaces;

import com.maik.WatchDogApi.domain.entities.User;
import com.maik.WatchDogApi.models.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findById(Long id);

    UserDTO findByEmail(String email);

    UserDTO save(User user);

    void delete(Long id);
}
