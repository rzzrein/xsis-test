package com.movies.rest.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "movies"
)
public class Movie {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private float rating;

    @Column
    private String image;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name= "updated_at")
    @UpdateTimestamp
    private Date updatedAt;
}
