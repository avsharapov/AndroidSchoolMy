package ru.gdgkazan.popularmoviesclean.domain.usecase;

import java.util.List;

import ru.gdgkazan.popularmoviesclean.domain.MoviesRepository;
import ru.gdgkazan.popularmoviesclean.domain.model.Video;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public class VideosUseCase {

    private final MoviesRepository mRepository;
    private final Observable.Transformer<List<Video>, List<Video>> mAsyncTransformer;

    public VideosUseCase(MoviesRepository repository,
                         Observable.Transformer<List<Video>, List<Video>> asyncTransformer) {
        mRepository = repository;
        mAsyncTransformer = asyncTransformer;
    }

    public Observable<List<Video>> videosMovies() {
        return mRepository.videosMovies()
                .compose(mAsyncTransformer);
    }
}


