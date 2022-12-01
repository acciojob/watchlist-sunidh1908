package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/movies/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody(required = true) Movie movie){
        movieService.addMovie(movie);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @PostMapping("/movies/add-director")
    public ResponseEntity<String> addDirector(@RequestBody(required = true) Director director){
        movieService.addDirector(director);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie") String movie,
                                                       @RequestParam("director") String director){
        try {
            movieService.addMovieDirector(movie, director);
        }
        catch (Exception e){
            return new ResponseEntity("Some error in method", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Successfully added Movie and Director",HttpStatus.CREATED);
    }

    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String movieName){
        return new ResponseEntity<>(movieService.getMovieByName(movieName),HttpStatus.ACCEPTED);
    }

    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String directorName){
        return new ResponseEntity<>(movieService.getDirectorByName(directorName),HttpStatus.ACCEPTED);
    }

    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("director") String directorName){
        return new ResponseEntity<>(movieService.getMoviesByDirectorName(directorName),HttpStatus.ACCEPTED);
    }

    @GetMapping("/movies/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        return new ResponseEntity<>(movieService.findAllMovies(),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@PathVariable("director") String directorName){
        movieService.deleteDirectorByName(directorName);
        return new ResponseEntity<>("Successfully Deleted the Director and all of his Movies",HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("All Directors deleted!!",HttpStatus.ACCEPTED);
    }

}
