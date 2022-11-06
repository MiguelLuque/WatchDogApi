package com.maik.WatchDogApi.web;

import com.maik.WatchDogApi.domain.services.interfaces.AnnouncementService;
import com.maik.WatchdogApi.api.AnnouncementsApiDelegate;
import com.maik.WatchdogApi.models.dto.AnnouncementDto;
import com.maik.WatchdogApi.models.dto.AnnouncementPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AnnouncementsApiDelegateImpl implements AnnouncementsApiDelegate {

    private final AnnouncementService announcementService;

    @Override
    //    @RolesAllowed({"ROLE_ADMIN", "ROLE_EDITOR", "ROLE_CUSTOMER"})
    public ResponseEntity<AnnouncementPage> findAllAnnouncements(Integer page, String title, String description, String postalCode, String name, String breed, String province, String location, String startDate, String endDate) {
        return ResponseEntity.ok(announcementService.findAll(page, title, description, postalCode, name, breed, province, location, startDate, endDate));
    }

    @Override
    @RolesAllowed({"ROLE_ADMIN", "ROLE_EDITOR", "ROLE_CUSTOMER"})
    public ResponseEntity<AnnouncementPage> findMyAnnouncements(Integer page, String title, String description, String postalCode, String name, String breed, String province, String location, String startDate, String endDate) {
        return ResponseEntity.ok(announcementService.findAllMyAnnouncements(page, title, description, postalCode, name, breed, province, location, startDate, endDate));
    }

    @Override
//    @RolesAllowed({"ROLE_ADMIN", "ROLE_EDITOR", "ROLE_CUSTOMER"})
    public ResponseEntity<AnnouncementDto> createAnnouncement(AnnouncementDto body) {
        return ResponseEntity.created(null).body(announcementService.save(body));
    }

    @Override
    @RolesAllowed({"ROLE_ADMIN", "ROLE_EDITOR", "ROLE_CUSTOMER"})
    public ResponseEntity<AnnouncementDto> deleteAnnouncement(Long announcementId) {
        announcementService.delete(announcementId);
        return ResponseEntity.ok().build();
    }

    @Override
//    @RolesAllowed({"ROLE_ADMIN", "ROLE_EDITOR", "ROLE_CUSTOMER"})
    public ResponseEntity<AnnouncementDto> findAnnouncementById(Long announcementId) {
        return ResponseEntity.ok(announcementService.findById(announcementId));
    }

    @Override
    @RolesAllowed({"ROLE_ADMIN", "ROLE_EDITOR", "ROLE_CUSTOMER"})
    public ResponseEntity<AnnouncementDto> updateAnnouncement(AnnouncementDto body) {
        return ResponseEntity.ok(announcementService.update(body));
    }


}
