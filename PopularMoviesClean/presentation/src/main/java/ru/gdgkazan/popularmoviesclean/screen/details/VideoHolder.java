package ru.gdgkazan.popularmoviesclean.screen.details;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gdgkazan.popularmoviesclean.R;
import ru.gdgkazan.popularmoviesclean.domain.model.Video;

/**
 * @author Artur Vasilov
 */
public class VideoHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.nameVideo)
    TextView mTailersNameTextView;

    @BindView(R.id.youtubeButton)
    Button mVideoButton;

    @NonNull
    public static VideoHolder create(@NonNull Context context) {
        View view = View.inflate(context, R.layout.video_item, null);

        return new VideoHolder(view);
    }

    private VideoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull Video video) {

       // Images.loadMovie(mTailersNameTextView, video, Images.WIDTH_185);
    }
}
