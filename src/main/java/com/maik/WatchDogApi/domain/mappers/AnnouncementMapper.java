package com.maik.WatchDogApi.domain.mappers;

import com.maik.WatchDogApi.domain.entities.Announcement;
import com.maik.WatchdogApi.models.dto.AnnouncementDto;
import com.maik.WatchdogApi.models.dto.AnnouncementPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnnouncementMapper {

    /**
     * To dto.
     *
     * @param target the target
     * @return the announcement dto
     */
    @Mapping(target = "picture", ignore = true)
    @Mapping(target = "ownerId", source = "target.user.id")
    AnnouncementDto toDto(Announcement target);

    /**
     * To dto.
     *
     * @param target the target
     * @return the announcement dto
     */
    @Mapping(target = "picture", ignore = true)
    @Mapping(target = "id", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
    @Mapping(target = "date", source = "target.date", dateFormat = "dd/MM/yyyy")
    Announcement toEntity(AnnouncementDto target);

    List<AnnouncementDto> toDto(List<Announcement> target);

    List<Announcement> toEntity(List<AnnouncementDto> target);
}

    
    