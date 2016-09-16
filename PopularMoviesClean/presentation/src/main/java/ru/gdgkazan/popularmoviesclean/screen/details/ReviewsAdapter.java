package ru.gdgkazan.popularmoviesclean.screen.details;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.gdgkazan.popularmoviesclean.domain.model.Review;

/**
 * @author Artur Vasilov
 */
public class ReviewsAdapter extends RecyclerView.Adapter<ReviewHolder> {

    private final List<Review> mReviews;

    private final OnItemClickListener mOnItemClickListener;

    private final View.OnClickListener mInternalListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Review review = (Review) view.getTag();
            mOnItemClickListener.onItemClick(view, review);
        }
    };

    public ReviewsAdapter(@NonNull OnItemClickListener onItemClickListener, @NonNull List<Review> reviews) {
        mReviews = reviews;

        mOnItemClickListener = onItemClickListener;
    }

    public void changeDataSet(@NonNull List<Review> reviews) {
        mReviews.clear();
        mReviews.addAll(reviews);
        notifyDataSetChanged();
    }

    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ReviewHolder.create(parent.getContext());
    }

    @Override
    public void onBindViewHolder(ReviewHolder holder, int position) {
        Review review = mReviews.get(position);
        holder.bind(review);

        holder.itemView.setOnClickListener(mInternalListener);
        holder.itemView.setTag(review);
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    public interface OnItemClickListener {

        void onItemClick(@NonNull View view, @NonNull Review review);

    }
}
