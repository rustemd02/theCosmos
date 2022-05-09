package ru.kpfu.itis.models.entities;

//import lombok.Builder;
//
//import java.util.Objects;
//
//@Builder
//public class Movie {
//    private Long id;
//    private String title;
//    private String description;
//    private String poster_link;
//    private String director;
//    private String age_restriction;
//
//}

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "poster_link")
    private String poster_link;

    @ManyToOne
    @JoinColumn(name = "director")
    private Director director;

    @Column(name = "age_restriction")
    private String age_restriction;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Seance> seances;

    @ManyToMany
    @JoinTable(name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @Fetch(value = FetchMode.JOIN)
    private List<Genre> genres;
}
