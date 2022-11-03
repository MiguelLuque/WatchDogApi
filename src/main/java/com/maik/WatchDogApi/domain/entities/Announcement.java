package com.maik.WatchDogApi.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.maik.WatchdogApi.models.dto.AnnouncementDto;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "announcement")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @NotBlank
    @Length(min = 10, max = 80)
    @Column(nullable = false, length = 80)
    private String title;

    @Length(max = 50)
    private String name;

    @NotBlank
    @Length(min = 10, max = 300)
    @Column(nullable = false, length = 80)
    private String description;

    @NotBlank
    @Column(nullable = false, length = 80)
    private String specie;

    @Length(max = 50)
    private String breed;

    @NotBlank
    @Column(nullable = false, length = 30)
    private String color;

    @Enumerated(EnumType.STRING)
    private AnnouncementDto.SizeEnum size;

    @Length(max = 100)
    private String street;

    @Length(min= 5, max = 10)
    private String postalCode;

    @Length(max = 50)
    private String contact;

    private String province;

    @NotBlank
    @Length(max = 50)
    private String location;

    @Temporal(TemporalType.DATE)
    @NotNull
    private Date date;

    private Boolean hasChip;
    @NotNull
    private boolean isOwner;


    private String picture;

    private void validate(){
        if(this.isOwner){
            if(this.name == null || this.name.isEmpty()){
                throw new IllegalArgumentException("Name is required");
            }
            if(this.hasChip == null){
                throw new IllegalArgumentException("hasChip is required");
            }
        }
    }
}
