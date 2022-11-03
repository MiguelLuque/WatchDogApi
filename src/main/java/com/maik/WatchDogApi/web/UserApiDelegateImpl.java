package com.maik.WatchDogApi.web;

import com.maik.WatchDogApi.domain.services.interfaces.AnnouncementService;
import com.maik.WatchdogApi.api.AnnouncementsApiDelegate;
import com.maik.WatchdogApi.api.UsersApiDelegate;
import com.maik.WatchdogApi.models.dto.AnnouncementDto;
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

//    @Override
//    @RolesAllowed({"ROLE_ADMIN", "ROLE_EDITOR", "ROLE_CUSTOMER"})
//    public ResponseEntity<List<AnnouncementDto>> findAllUserAnnouncements(Long userId, Integer page, String postalCode, String name, String breed, String globalSearch) {
//        return ResponseEntity.ok(announcementService.findAllByUserId(userId, page, postalCode, name, breed, globalSearch));
//    }


}
