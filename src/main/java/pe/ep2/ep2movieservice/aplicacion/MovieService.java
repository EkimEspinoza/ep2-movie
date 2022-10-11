package pe.ep2.ep2movieservice.aplicacion;

import pe.ep2.ep2movieservice.infraestructura.request.MovieRequest;
import pe.ep2.ep2movieservice.infraestructura.response.BasicResponse;
import pe.ep2.ep2movieservice.infraestructura.response.MovieResponse;

public interface MovieService {

    public BasicResponse addMovie(MovieRequest request);
    public MovieResponse getAll();
    public MovieResponse getByTitle(String title);

    public BasicResponse deleteMovie(String title);

    public BasicResponse updateMovie(MovieRequest request, String title);

}
