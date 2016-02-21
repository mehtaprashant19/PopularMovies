package com.udacity.popularmovies2.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.udacity.popularmovies2.fragment.DetailFragment;
import com.udacity.popularmovies2.R;


public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(DetailFragment.DETAIL_MOVIE,
                    getIntent().getParcelableExtra(DetailFragment.DETAIL_MOVIE));

            DetailFragment fragment = new DetailFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.flMovieDetail, fragment)
                    .commit();
        }
    }
}
