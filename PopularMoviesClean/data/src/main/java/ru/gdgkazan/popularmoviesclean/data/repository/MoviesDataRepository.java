package ru.gdgkazan.popularmoviesclean.data.repository;

import java.util.List;

import ru.gdgkazan.popularmoviesclean.data.cache.MoviesCacheTransformer;
import ru.gdgkazan.popularmoviesclean.data.cache.ReviewsCacheTransformer;
import ru.gdgkazan.popularmoviesclean.data.cache.VideosCacheTransformer;
import ru.gdgkazan.popularmoviesclean.data.mapper.MoviesMapper;
import ru.gdgkazan.popularmoviesclean.data.mapper.ReviewsMapper;
import ru.gdgkazan.popularmoviesclean.data.mapper.VideosMapper;
import ru.gdgkazan.popularmoviesclean.data.model.response.MoviesResponse;
import ru.gdgkazan.popularmoviesclean.data.model.response.ReviewsResponse;
import ru.gdgkazan.popularmoviesclean.data.model.response.VideosResponse;
import ru.gdgkazan.popularmoviesclean.data.network.ApiFactory;
import ru.gdgkazan.popularmoviesclean.domain.MoviesRepository;
import ru.gdgkazan.popularmoviesclean.domain.model.Movie;
import ru.gdgkazan.popularmoviesclean.domain.model.Review;
import ru.gdgkazan.popularmoviesclean.domain.model.Video;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public class MoviesDataRepository implements MoviesRepository {

    @Override
    public Observable<List<Movie>> popularMovies() {
        return ApiFactory.getMoviesService()
                .popularMovies()
                .map(MoviesResponse::getMovies)
                .compose(new MoviesCacheTransformer())
                .flatMap(Observable::from)
                .map(new MoviesMapper())
                .toList();
    }

    @Override
    public Observable<List<Review>> reviewsMovies() {
        return ApiFactory.getMoviesService()
                .reviewsMovies()
                .map(ReviewsResponse::getReviews)
                .compose(new ReviewsCacheTransformer())
                .flatMap(Observable::from)
                .map(new ReviewsMapper())
                .toList();
    }

    @Override
    public Observable<List<Video>> videosMovies() {
        return ApiFactory.getMoviesService()
                .videosMovies()
                .map(VideosResponse::getVideos)
                .compose(new VideosCacheTransformer())
                .flatMap(Observable::from)
                .map(new VideosMapper())
                .toList();
    }
}

