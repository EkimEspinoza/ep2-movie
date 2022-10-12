package pe.ep2.ep2movieservice.aplicacion;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.ep2.ep2movieservice.dominio.Movie;
import pe.ep2.ep2movieservice.infraestructura.repository.MovieRepository;
import pe.ep2.ep2movieservice.infraestructura.request.MovieRequest;
import pe.ep2.ep2movieservice.infraestructura.response.BasicResponse;
import pe.ep2.ep2movieservice.infraestructura.response.MovieResponse;

import javax.transaction.Transactional;
import java.util.List;

@Log4j2
@Service

public class MovieServiceImpl implements MovieService{
    private static final String Password ="123456";
    @Autowired
    private MovieRepository movieRepository;
    @Override
    public BasicResponse addMovie(MovieRequest request) {
        try {
            if(request.getPassword().equals(Password))
            {
                movieRepository.save(this.builMovieFromRequest(request));
                return BasicResponse.whenSuccess() ;
            }
            else{
                return BasicResponse.whenPassNotMatch() ;
            }
        }
        catch (Exception ex){
            log.error(ex.getMessage());
            return BasicResponse.whenError(ex.getMessage()) ;

        }
    }
    public Movie builMovieFromRequest(MovieRequest request){
        return Movie.builder()
                .title(request.getTitle())
                .year(request.getYear())
                .genre(request.getGenre())
                .director(request.getDirector())
                .rating(request.getRating()).build();
    }
    @Override
    public MovieResponse getAll(){

        List<Movie> movieList = movieRepository.findAll();


        if(movieList.isEmpty()){
            return MovieResponse.builder()
                    .movieList(null)
                    .basicResponse(BasicResponse.whenNoDataFound("User"))
                    .build();
        }

        return MovieResponse.builder()
                .movieList(movieList)
                .basicResponse(BasicResponse.whenSuccess())
                .build();
    }
    @Override
    public MovieResponse getByTitle(String title){
        try {
            Movie  movie = movieRepository.findByTitle(title);

            if(movie != null){
                return  MovieResponse.builder()
                        .movieList(List.of(movie))
                        .basicResponse(BasicResponse.whenSuccess())
                        .build();
            }
            else{
                return MovieResponse.builder()
                        .movieList(null)
                        .basicResponse(BasicResponse.whenNoDataFound("Movie"))
                        .build();
            }
        }
        catch (Exception ex){
            return MovieResponse.builder()
                    .movieList(null)
                    .basicResponse(BasicResponse.whenError(ex.getMessage()))
                    .build();
        }
    }

    public BasicResponse deleteMovie(String title){
        try {
            Movie movie = movieRepository.findByTitle(title);
            if(title == null){
                return BasicResponse.whenNoDataFound("Pelicula con nombre " + title);
            }else{
                movieRepository.delete(movie);
                return BasicResponse.whenSuccess();
            }
        }
        catch (Exception ex){
            return BasicResponse.whenError(ex.getMessage());
        }

    }

    @Transactional
    @Override
    public BasicResponse updateMovie(MovieRequest request , String title){
        try {
            //Validar que exista la pelicula
            Movie movie =  movieRepository.findByTitle(title);
            if(movie == null ){
                return BasicResponse.whenNoDataFound("Pelicula " + title);
            } else{
                // validar que la clave sea correcta
                if(request.getPassword().equals(Password)){
                    movie.setDirector(request.getDirector()!= null &&  !request.getDirector().isBlank() ? request.getDirector() : movie.getDirector());
                    movie.setGenre(request.getGenre()!= null &&  !request.getGenre().isBlank() ? request.getGenre() : movie.getGenre());
                    movie.setTitle(request.getTitle()!= null &&  !request.getTitle().isBlank() ? request.getTitle() : movie.getTitle());
                    movie.setYear(movie.getYear());
                    movie.setRating(movie.getRating());

                    return BasicResponse.whenSuccess();
                }
                else{
                    return BasicResponse.whenPassNotMatch();
                }
            }
    }
        catch (Exception ex){
            return BasicResponse.whenError(ex.getMessage());
        }
    }
}
