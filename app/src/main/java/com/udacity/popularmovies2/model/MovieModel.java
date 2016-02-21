package com.udacity.popularmovies2.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;


import com.udacity.popularmovies2.fragment.MainFragment;

import org.json.JSONException;
import org.json.JSONObject;

public class MovieModel implements Parcelable {

    private int id;
    private String original_title;
    private String poster_path;
    private String backdrop_path;
    private String overview;
    private int vote_average;
    private String release_date;

    public MovieModel() {

    }

    public MovieModel(JSONObject movie) throws JSONException {
        this.id = movie.getInt("id");
        this.original_title = movie.getString("original_title");
        this.poster_path = movie.getString("poster_path");
        this.backdrop_path = movie.getString("backdrop_path");
        this.overview = movie.getString("overview");
        this.vote_average = movie.getInt("vote_average");
        this.release_date = movie.getString("release_date");
    }

    public MovieModel(Cursor cursor) {
        this.id = cursor.getInt(MainFragment.COL_MOVIE_ID);
        this.original_title = cursor.getString(MainFragment.COL_TITLE);
        this.poster_path = cursor.getString(MainFragment.COL_IMAGE);
        this.backdrop_path = cursor.getString(MainFragment.COL_IMAGE2);
        this.overview = cursor.getString(MainFragment.COL_OVERVIEW);
        this.vote_average = cursor.getInt(MainFragment.COL_RATING);
        this.release_date = cursor.getString(MainFragment.COL_DATE);
    }

    public int getId() {
        return id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public int getVote_average() {
        return vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(original_title);
        dest.writeString(poster_path);
        dest.writeString(backdrop_path);
        dest.writeString(overview);
        dest.writeInt(vote_average);
        dest.writeString(release_date);
    }

    public static final Parcelable.Creator<MovieModel> CREATOR
            = new Parcelable.Creator<MovieModel>() {
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    private MovieModel(Parcel in) {
        id = in.readInt();
        original_title = in.readString();
        poster_path = in.readString();
        backdrop_path = in.readString();
        overview = in.readString();
        vote_average = in.readInt();
        release_date = in.readString();
    }
}
