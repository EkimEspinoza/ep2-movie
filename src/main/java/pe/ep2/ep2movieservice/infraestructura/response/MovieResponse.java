package pe.ep2.ep2movieservice.infraestructura.response;

import lombok.Builder;
import lombok.Data;
import pe.ep2.ep2movieservice.dominio.Movie;

import java.util.List;

@Builder
@Data
public class MovieResponse {
    private List<Movie> movieList;
    private BasicResponse basicResponse;
}

