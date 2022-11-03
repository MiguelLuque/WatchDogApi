package com.maik.WatchDogApi.domain.repositories;

import com.maik.WatchDogApi.domain.entities.Announcement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    Optional<Announcement> findByName(String name);

    Slice<Announcement> findAllByPostalCodeOrBreedOrName(Pageable pageable, String postalCode,String breed,String name);


}
