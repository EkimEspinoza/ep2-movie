package pe.ep2.ep2movieservice.dominio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(
        name ="tbl_Movie",
        uniqueConstraints = {
                @UniqueConstraint(name="movie_title_unique",columnNames = "title"),
        }
)


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class Movie {
    @SequenceGenerator(
            name="movie_sequence",
            sequenceName = "movie_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "movie_sequence"
    )
    @Id
    Long id;
    String title;
    int year;
    String genre;
    String director;
    int rating;
}


