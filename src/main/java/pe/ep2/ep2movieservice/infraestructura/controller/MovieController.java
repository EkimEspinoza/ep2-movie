package pe.ep2.ep2movieservice.infraestructura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.ep2.ep2movieservice.aplicacion.MovieService;
import pe.ep2.ep2movieservice.infraestructura.request.MovieRequest;
import pe.ep2.ep2movieservice.infraestructura.response.BasicResponse;
import pe.ep2.ep2movieservice.infraestructura.response.MovieResponse;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping
    @RequestMapping("/addmovie")
    public ResponseEntity<BasicResponse> addmovie(@RequestBody MovieRequest request){
        BasicResponse response = movieService.addMovie(request);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @GetMapping
    @RequestMapping("/getall")
    public MovieResponse getAll(){
        return movieService.getAll();
    }

    @GetMapping
    @RequestMapping("/getbytitle")
    public MovieResponse getByTitle(@RequestParam String title){
        return movieService.getByTitle(title) ;
    }

    @DeleteMapping
    @RequestMapping("/deletemovie")
    public ResponseEntity<BasicResponse> deleteMovie(@RequestParam String title){
        BasicResponse response = movieService.deleteMovie(title);
        return ResponseEntity.status(response.getCode()).body(response);
    }


    @PutMapping
    @RequestMapping("/updatemovie")
    public ResponseEntity<BasicResponse> updateMovie(@RequestBody MovieRequest request,
                                                    @RequestParam String title){
        BasicResponse response = movieService.updateMovie(request,title);
        return ResponseEntity.status(response.getCode()).body(response);
    }

}
