package ru.kpfu.itis.models.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;


@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "users")
@Entity
@Table(name = "seance")
public class Seance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Column(name = "time")
    private Timestamp time;

    @Column(name = "ticketsAmount")
    private Integer ticketsAmount;

    @Column(name = "price")
    private Integer price;

//    @ManyToMany
//    @JoinTable(name = "user_seance",
//            joinColumns = @JoinColumn(name = "seance_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private List<User> users;
}
