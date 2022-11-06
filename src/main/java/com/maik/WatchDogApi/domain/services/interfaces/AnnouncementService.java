package com.maik.WatchDogApi.domain.services.interfaces;

import com.maik.WatchdogApi.models.dto.AnnouncementDto;
import com.maik.WatchdogApi.models.dto.AnnouncementPage;

import java.util.List;

public interface AnnouncementService {

    AnnouncementPage findAll(Integer page, String title, String description, String postalCode, String name, String breed, String province, String location, String startDate, String endDate);

    AnnouncementPage findAllByUserId(Long userId, Integer page, String title, String description, String postalCode, String name, String breed, String province, String location, String startDate, String endDate);

    AnnouncementPage findAllMyAnnouncements(Integer page, String title, String description, String postalCode, String name, String breed, String province, String location, String startDate, String endDate);

    AnnouncementDto findById(Long id);

    AnnouncementDto findByName(String name);

    AnnouncementDto save(AnnouncementDto petDTO);

    AnnouncementDto update(AnnouncementDto petDTO);

    void delete(Long id);

}
