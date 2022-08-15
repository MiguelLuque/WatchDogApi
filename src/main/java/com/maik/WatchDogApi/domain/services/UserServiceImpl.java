package com.maik.WatchDogApi.domain.services;

import com.maik.WatchDogApi.domain.entities.User;
import com.maik.WatchDogApi.domain.mappers.UserMapper;
import com.maik.WatchDogApi.domain.repositories.UserRepository;
import com.maik.WatchDogApi.domain.services.interfaces.UserService;
import com.maik.WatchDogApi.models.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public List<UserDTO> findAll() {
        return null;
    }

    @Override
    public UserDTO findById(Long id) {
        return userRepository.findById(id).map(userMapper::toDto).orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @Override
    public UserDTO findByEmail(String email) {

        return userRepository.findByEmail(email).map(userMapper::toDto).orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @Override
    public UserDTO save(User user) {
        if(checkIfUserExists(user.getEmail())) {
            throw new IllegalArgumentException("User already exists");
        }
        User newUser = userRepository.save(user);
        return userMapper.toDto(newUser);
    }

    @Override
    public void delete(Long id) {

    }

    private boolean checkIfUserExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }


}
