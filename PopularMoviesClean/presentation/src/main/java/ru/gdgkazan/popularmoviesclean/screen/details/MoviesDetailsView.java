package ru.gdgkazan.popularmoviesclean.screen.details;

import android.support.annotation.NonNull;

import java.util.List;

import ru.gdgkazan.popularmoviesclean.domain.model.Review;
import ru.gdgkazan.popularmoviesclean.domain.model.Video;
import ru.gdgkazan.popularmoviesclean.screen.general.LoadingView;

/**
 * @author Artur Vasilov
 */
public interface MoviesDetailsView extends LoadingView {

    void showTrailers(@NonNull List<Video> video);
    void showReviews(@NonNull List<Review> review);

    void showError();

}


