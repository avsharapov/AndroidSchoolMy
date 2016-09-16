package ru.gdgkazan.popularmoviesclean.domain;

import java.util.List;

import rx.Observable;

/**
 * @author Artur Vasilov
 */
public interface MoviesRepository {

    Observable<List<ru.gdgkazan.popularmoviesclean.domain.model.Movie>> popularMovies();
    Observable<List<ru.gdgkazan.popularmoviesclean.domain.model.Review>> reviewsMovies();
    Observable<List<ru.gdgkazan.popularmoviesclean.domain.model.Video>> videosMovies();

}
