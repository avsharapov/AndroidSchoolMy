package ru.gdgkazan.popularmoviesclean.screen.details;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gdgkazan.popularmoviesclean.R;
import ru.gdgkazan.popularmoviesclean.domain.model.Review;

/**
 * @author Artur Vasilov
 */
public class ReviewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.nameReview)
    TextView mReviewNameTextView;

    @BindView(R.id.textReview)
    TextView mReviewTextView;

    @NonNull
    public static ReviewHolder create(@NonNull Context context) {
        View view = View.inflate(context, R.layout.review_item, null);

        return new ReviewHolder(view);
    }

    private ReviewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull Review review) {

        //Images.loadMovie(mReviewNameTextView, review, Images.WIDTH_185);
    }
}
