package com.maik.WatchDogApi.domain.mappers;

import com.maik.WatchDogApi.domain.entities.Announcement;
import com.maik.WatchdogApi.models.dto.AnnouncementDto;
import com.maik.WatchdogApi.models.dto.AnnouncementPage;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public class MapperUtils {

    public static AnnouncementPage toAnnouncementPage(Integer totalPages, Integer page, List<AnnouncementDto> announcementDtos) {

        AnnouncementPage result = new AnnouncementPage();

        result.setTotalPages(BigDecimal.valueOf(totalPages));
        result.setCurrentPage(BigDecimal.valueOf(page));
        result.setContent(announcementDtos);

        return result;

    }



}
