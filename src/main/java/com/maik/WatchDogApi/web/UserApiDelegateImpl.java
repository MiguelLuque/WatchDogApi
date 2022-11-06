package com.maik.WatchDogApi.web;

import com.maik.WatchDogApi.domain.services.interfaces.AnnouncementService;
import com.maik.WatchdogApi.api.AnnouncementsApiDelegate;
import com.maik.WatchdogApi.api.UsersApiDelegate;
import com.maik.WatchdogApi.models.dto.AnnouncementDto;
import com.maik.WatchdogApi.models.dto.AnnouncementPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserApiDelegateImpl implements UsersApiDelegate {

    private final AnnouncementService announcementService;

    @Override
    @RolesAllowed({"ROLE_ADMIN", "ROLE_EDITOR"})

    public ResponseEntity<AnnouncementPage> findAllUserAnnouncements(Integer page, Long userId, String title, String description, String postalCode, String name, String breed, String province, String location, String startDate, String endDate) {
        return ResponseEntity.ok(announcementService.findAllByUserId(userId, page, title, description, postalCode, name, breed, province, location, startDate, endDate));
    }
}
