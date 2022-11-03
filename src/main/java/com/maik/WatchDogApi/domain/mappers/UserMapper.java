package com.maik.WatchDogApi.domain.mappers;

import com.maik.WatchDogApi.domain.entities.User;
import com.maik.WatchdogApi.models.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * To dto.
     *
     * @param target the target
     * @return the pet dto
     */
    UserDTO toDto(User target);

    /**
     * To dto.
     *
     * @param target the target
     * @return the pet dto
     */
    User toEntity(UserDTO target);

    List<UserDTO> toDto(List<User> target);

    List<User> toEntity(List<UserDTO> target);
}

    
    