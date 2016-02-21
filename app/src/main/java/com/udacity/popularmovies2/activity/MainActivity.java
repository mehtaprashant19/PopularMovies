package com.udacity.popularmovies2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.udacity.popularmovies2.R;
import com.udacity.popularmovies2.fragment.DetailFragment;
import com.udacity.popularmovies2.fragment.MainFragment;

import com.udacity.popularmovies2.model.MovieModel;

public class MainActivity extends AppCompatActivity implements MainFragment.Callback {

    private boolean mTwoPane;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.flMovieDetail) != null) {
            mTwoPane = true;
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.flMovieDetail, new DetailFragment(),
                                DetailFragment.TAG)
                        .commit();
            }
        } else {
            mTwoPane = false;
        }
    }

    @Override
    public void onItemSelected(MovieModel movie) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(DetailFragment.DETAIL_MOVIE, movie);

            DetailFragment fragment = new DetailFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flMovieDetail, fragment, DetailFragment.TAG)
                    .commit();
        } else {
            Intent intent = new Intent(this, DetailActivity.class)
                    .putExtra(DetailFragment.DETAIL_MOVIE, movie);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
