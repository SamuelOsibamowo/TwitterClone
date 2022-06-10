package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Movie;
import android.os.Bundle;
import android.os.Parcel;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class DetailedActivity extends AppCompatActivity {

    Tweet tweet;
    TextView tvName;
    TextView tvScreenName;
    TextView tvBody;
    ImageView ivProfileImage;
    ImageView ivTweetImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));
        tvName = findViewById(R.id.tvName);
        tvScreenName = findViewById(R.id.tvScreenName);
        tvBody = findViewById(R.id.tvBody);
        ivProfileImage = findViewById(R.id.ivProfileImage);
        ivTweetImage = findViewById(R.id.ivTweetImage);


        tvName.setText(tweet.user.name);
        tvScreenName.setText(tweet.user.screenName);
        tvBody.setText(tweet.body);


        String profileImageUrl = tweet.user.profileImageUrl;
        String tweetImageUrl = tweet.imageUrl;
        int radius = 50; // corner radius, higher value = more rounded
        Glide.with(this)
                .load(profileImageUrl)
                .transform(new RoundedCorners(radius))
                .into(ivProfileImage);

        Glide.with(this)
                .load(tweetImageUrl)
                .into(ivTweetImage);

    }
}