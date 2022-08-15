package com.maik.WatchDogApi.domain.services.interfaces;

import com.maik.WatchDogApi.models.dto.PetDTO;

import java.util.List;

public interface PetService {

    List<PetDTO> findAll();

    PetDTO findById(Long id);

    PetDTO findByName(String name);

    PetDTO save(PetDTO petDTO);

    PetDTO update(PetDTO petDTO);

    void delete(Long id);

}
