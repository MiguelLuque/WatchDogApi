package com.maik.WatchDogApi.domain.repositories;

import com.maik.WatchDogApi.domain.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    Optional<Pet> findByName(String name);
}
