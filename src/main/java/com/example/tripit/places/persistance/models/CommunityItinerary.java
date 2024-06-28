package com.example.tripit.places.persistance.models;

import com.example.tripit.core.persistance.models.User;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "community_itineraries")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommunityItinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "itinerary_id")
    private Integer itineraryId;

    @Column(name = "publish_date")
    private String publishDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id")
    private User publisher;

    @Column(name = "description")
    private String description;
}
