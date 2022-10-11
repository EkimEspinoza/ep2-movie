package pe.ep2.ep2movieservice.infraestructura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.ep2.ep2movieservice.dominio.Movie;

public interface MovieRepository  extends JpaRepository<Movie,Long> {
    public Movie findByTitle(String title);

}
