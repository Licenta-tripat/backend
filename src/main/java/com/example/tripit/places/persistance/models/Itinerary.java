package com.example.tripit.places.persistance.models;

import com.example.tripit.core.persistance.models.User;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "itineraries")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Itinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "no_days")
    private Integer noDays;

    @Column(name = "location_id")
    private String locationId;

    @Column(name = "location_name")
    private String locationName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "photo_reference")
    private String photoReference;

    @Column(name = "locations_number")
    private int locationsNumber;
}
