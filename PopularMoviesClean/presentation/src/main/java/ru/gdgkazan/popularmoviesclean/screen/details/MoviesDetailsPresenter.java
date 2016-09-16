package ru.gdgkazan.popularmoviesclean.screen.details;

import android.support.annotation.NonNull;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.gdgkazan.popularmoviesclean.R;
import ru.gdgkazan.popularmoviesclean.domain.usecase.ReviewsUseCase;
import ru.gdgkazan.popularmoviesclean.domain.usecase.VideosUseCase;

/**
 * @author Artur Vasilov
 */
public class MoviesDetailsPresenter {

    private final MoviesDetailsView mMoviesDetailsView;
    private final ReviewsUseCase mReviewsUseCase;
    private final VideosUseCase mVideosUseCase;
    private final LifecycleHandler mLifecycleHandler;

    public MoviesDetailsPresenter(@NonNull MoviesDetailsView moviesDetailsView, @NonNull ReviewsUseCase reviewsUseCase, @NonNull VideosUseCase videosUseCase,
                                  @NonNull LifecycleHandler lifecycleHandler) {
        mMoviesDetailsView = moviesDetailsView;
        mReviewsUseCase = reviewsUseCase;
        mVideosUseCase = videosUseCase;
        mLifecycleHandler = lifecycleHandler;
    }

    public void init() {

        mReviewsUseCase.reviewsMovies()
                .doOnSubscribe(mMoviesDetailsView::showLoadingIndicator)
                .doAfterTerminate(mMoviesDetailsView::hideLoadingIndicator)
                .compose(mLifecycleHandler.load(R.id.movies_request_id))
                .subscribe(mMoviesDetailsView::showReviews, throwable -> mMoviesDetailsView.showError());
        mVideosUseCase.videosMovies()
                .doOnSubscribe(mMoviesDetailsView::showLoadingIndicator)
                .doAfterTerminate(mMoviesDetailsView::hideLoadingIndicator)
                .compose(mLifecycleHandler.load(R.id.movies_request_id))
                .subscribe(mMoviesDetailsView::showTrailers, throwable -> mMoviesDetailsView.showError());

    }
}

