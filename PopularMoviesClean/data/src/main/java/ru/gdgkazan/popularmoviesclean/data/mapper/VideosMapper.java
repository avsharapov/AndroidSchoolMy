package ru.gdgkazan.popularmoviesclean.data.mapper;


import ru.gdgkazan.popularmoviesclean.domain.model.Video;
import rx.functions.Func1;

/**
 * @author Artur Vasilov
 */
public class VideosMapper implements Func1<ru.gdgkazan.popularmoviesclean.data.model.content.Video, Video> {

    @Override
    public Video call(ru.gdgkazan.popularmoviesclean.data.model.content.Video video) {
        return new Video(video.getKey(), video.getName());
    }
}
