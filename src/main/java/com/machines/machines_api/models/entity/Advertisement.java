package com.machines.machines_api.models.entity;

import com.machines.machines_api.models.baseEntity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "advertisements")
public class Advertisement extends BaseEntity {
    private String title;          // Title or name of the advertisement
    @NotNull(message = "Снимки за рекламата са задължителни")
    @Size(min = 1, message = "Поне една снимка за рекламата е задължителна")
    @ManyToMany
    @JoinTable(
            name = "advertisements_pictures",
            joinColumns = @JoinColumn(name = "advertisement_id"),
            inverseJoinColumns = @JoinColumn(name = "file_id")
    )
    private Set<File> pictures;
    private String targetUrl;      // URL to the site when the ad is clicked
    private String position;       // Position of the ad on the site (e.g., "Top", "Center", "Bottom", "Gallery")
    private LocalDate startDate;   // Start date for the ad campaign
    private LocalDate endDate;     // End date for the ad campaign
    private boolean active;        // Whether the ad is active or not
}