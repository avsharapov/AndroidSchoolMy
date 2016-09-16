package ru.gdgkazan.popularmoviesclean.data.network;

import retrofit2.http.GET;
import ru.gdgkazan.popularmoviesclean.data.model.response.MoviesResponse;
import ru.gdgkazan.popularmoviesclean.data.model.response.ReviewsResponse;
import ru.gdgkazan.popularmoviesclean.data.model.response.VideosResponse;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public interface MovieService {

    @GET("popular/")
    Observable<MoviesResponse> popularMovies();
    @GET("reviews/")
    Observable<ReviewsResponse> reviewsMovies();
    @GET("videos/")
    Observable<VideosResponse> videosMovies();
}
