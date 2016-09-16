package ru.gdgkazan.popularmoviesclean.screen.details;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.gdgkazan.popularmoviesclean.domain.model.Video;

/**
 * @author Artur Vasilov
 */
public class VideosAdapter extends RecyclerView.Adapter<VideoHolder> {

    private final List<Video> mVideos;

    private final OnItemClickListener mOnItemClickListener;

    private final View.OnClickListener mInternalListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Video video = (Video) view.getTag();
            mOnItemClickListener.onItemClick(view, video);
        }
    };

    public VideosAdapter(@NonNull OnItemClickListener onItemClickListener, @NonNull List<Video> videos) {
        mVideos = videos;
        mOnItemClickListener = onItemClickListener;
    }

    public void changeDataSet(@NonNull List<Video> videos) {
        mVideos.clear();
        mVideos.addAll(videos);
        notifyDataSetChanged();
    }

    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return VideoHolder.create(parent.getContext());
    }

    @Override
    public void onBindViewHolder(VideoHolder holder, int position) {
        Video video = mVideos.get(position);
        holder.bind(video);

        holder.itemView.setOnClickListener(mInternalListener);
        holder.itemView.setTag(video);
    }

    @Override
    public int getItemCount() {
        return mVideos.size();
    }

    public interface OnItemClickListener {

        void onItemClick(@NonNull View view, @NonNull Video video);

    }
}
