package pe.ep2.ep2movieservice.infraestructura.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MovieRequest {
    private Long id;
    private String title;
    private int year;
    private String genre;
    private String director;
    private int rating;
    private String password ;
}
