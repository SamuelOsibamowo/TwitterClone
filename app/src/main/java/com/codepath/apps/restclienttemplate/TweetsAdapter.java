package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder>{

    Context context;
    List<Tweet> tweets;
    public TweetsAdapter(Context context, List<Tweet> tweets){
        this.context = context;
        this.tweets = tweets;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tweet tweet = tweets.get(position);
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView ivProfileImage;
        ImageView ivTweetImage;
        TextView tvBody;

        TextView ivRetweetCount;
        TextView ivLikeCount;
        TextView tvName;
        TextView tvScreenName;
        TextView tvTimeStamp;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ivRetweetCount = itemView.findViewById(R.id.ivRetweetCount);
            ivLikeCount = itemView.findViewById(R.id.ivLikeCount);

            tvTimeStamp = itemView.findViewById(R.id.tvTimeStamp);
            ivTweetImage = itemView.findViewById(R.id.ivTweetImage);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvName = itemView.findViewById(R.id.tvName);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);


        }

        // method focuses on attaching imageURLs to their appropriate imageView
        public void bind(Tweet tweet) {


            tvTimeStamp.setText(tweet.timeStamp);


            ivRetweetCount.setText(tweet.retweetCount);
            ivLikeCount.setText(tweet.likeCount);


            tvName.setText(tweet.user.name);
            tvBody.setText(tweet.body);
            // only display the screen name if space allows for it
            if (tweet.user.charsLeft > tweet.user.screenName.length()) {
                tvScreenName.setText(tweet.user.screenName);
            }

            int Radius = 50;
            Glide.with(context)
                    .load(tweet.user.profileImageUrl)
                    .transform(new RoundedCorners(Radius))
                    .into(ivProfileImage);


            Glide.with(context)
                    .load(tweet.imageUrl)
                    .into(ivTweetImage);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION){
                Tweet tweet = tweets.get(position);
                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra(Tweet.class.getSimpleName(), Parcels.wrap(tweet));
                context.startActivity(intent);
            }
        }
    }

    // Clean all elements of the recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> list) {
        tweets.addAll(list);
        notifyDataSetChanged();
    }


}
