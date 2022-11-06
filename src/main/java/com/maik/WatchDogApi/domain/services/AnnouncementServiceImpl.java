package com.maik.WatchDogApi.domain.services;

import com.maik.WatchDogApi.domain.entities.Announcement;
import com.maik.WatchDogApi.domain.entities.User;
import com.maik.WatchDogApi.domain.mappers.AnnouncementMapper;
import com.maik.WatchDogApi.domain.mappers.MapperUtils;
import com.maik.WatchDogApi.domain.repositories.AnnouncementRepository;
import com.maik.WatchDogApi.domain.repositories.UserRepository;
import com.maik.WatchDogApi.domain.services.interfaces.AnnouncementService;
import com.maik.WatchDogApi.domain.services.interfaces.UserService;
import com.maik.WatchdogApi.models.dto.AnnouncementDto;
import com.maik.WatchdogApi.models.dto.AnnouncementPage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final UserService userService;

    private final AnnouncementMapper announcementMapper;

    @Override
    public AnnouncementPage findAll(Integer page, String title, String description, String postalCode, String name, String breed, String province, String location, String startDate, String endDate) {
        // TODO: Allow filtering by any field with general search
        // TODO: Set picture attribute as request part and store it in the database
        Pageable pageParams = PageRequest.of(page, 10, Sort.by("date").descending());
        Announcement announcement = Announcement.builder()
                .title(title)
                .description(description)
                .postalCode(postalCode)
                .name(name)
                .breed(breed)
                .province(province)
                .location(location)
                .build();
        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase()
                .withIgnorePaths("isOwner")
                .withIgnoreNullValues();
        Page<Announcement> res =  announcementRepository.findAll(Example.of(announcement, matcher), pageParams);
        List<AnnouncementDto> announcementDtos = announcementMapper.toDto(res.getContent());
        return MapperUtils.toAnnouncementPage(res.getTotalPages(), res.getNumber(), announcementDtos);
    }


    @Override
    public AnnouncementPage findAllByUserId(Long userId, Integer page, String title, String description, String postalCode, String name, String breed, String province, String location, String startDate, String endDate) {
        Pageable pageParams = PageRequest.of(page, 10, Sort.by("date").descending());
        Announcement announcement = Announcement.builder()
                .title(title)
                .description(description)
                .postalCode(postalCode)
                .name(name)
                .breed(breed)
                .province(province)
                .location(location)
                .user(userService.findUserById(userId))
                .build();
        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnorePaths("isOwner")
                .withIgnoreNullValues();
        Page<Announcement> res =  announcementRepository.findAll(Example.of(announcement, matcher), pageParams);
        List<AnnouncementDto> announcementDtos = announcementMapper.toDto(res.getContent());
        return MapperUtils.toAnnouncementPage(res.getTotalPages(), res.getNumber(), announcementDtos);
    }

    @Override
    public AnnouncementPage findAllMyAnnouncements(Integer page, String title, String description, String postalCode, String name, String breed, String province, String location, String startDate, String endDate) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.findAllByUserId(currentUser.getId(), page, title, description, postalCode, name, breed, province, location, startDate, endDate);
    }

    @Override
    public AnnouncementDto findById(Long id) {
        return announcementRepository.findById(id).map(announcementMapper::toDto).orElseThrow(() -> new NoSuchElementException("Announcement not found"));
    }


    @Override
    public AnnouncementDto findByName(String name) {
        return announcementRepository.findByName(name).map(announcementMapper::toDto).orElseThrow(() -> new NoSuchElementException("Announcement not found"));
    }

    @Override
    public AnnouncementDto save(AnnouncementDto AnnouncementDto) {

        Announcement announcement = announcementMapper.toEntity(AnnouncementDto);

        //get principal user

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        announcement.setUser(principal);


        if(announcement.getId() != null && checkIfExists(announcement.getId()).isPresent()) {
            throw new IllegalArgumentException("Announcement already exists");
        }
        Announcement newAnnouncement = announcementRepository.save(announcement);
        return announcementMapper.toDto(newAnnouncement);
    }

    @Override
    public AnnouncementDto update(AnnouncementDto announcementDto) {
        // TODO: Dont allow change owner
        Announcement announcement = announcementMapper.toEntity(announcementDto);
        if(!checkIfExists(announcement.getId()).isPresent()) {
            throw new IllegalArgumentException("Announcement not found");
        }
        //check if is null
        if(announcement.getId() == null) {
            throw new IllegalArgumentException("Announcement id cannot be null");
        }
        announcement = announcementRepository.save(announcement);
        return announcementMapper.toDto(announcement);
    }

    @Override
    public void delete(Long id) {
        announcementRepository.deleteById(id);
    }

    private Optional<Announcement> checkIfExists(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Id must not be null");
        }
        return announcementRepository.findById(id);
    }


}
