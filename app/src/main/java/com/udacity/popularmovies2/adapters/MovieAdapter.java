package com.udacity.popularmovies2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.udacity.popularmovies2.R;
import com.udacity.popularmovies2.model.MovieModel;

import java.util.List;

public class MovieAdapter extends BaseAdapter {

    private final Context mContext;
    private final LayoutInflater mInflater;

    private final MovieModel mLock = new MovieModel();

    private List<MovieModel> mObjects;

    public MovieAdapter(Context context, List<MovieModel> objects) {
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mObjects = objects;
    }

    public Context getContext() {
        return mContext;
    }

    public void add(MovieModel object) {
        synchronized (mLock) {
            mObjects.add(object);
        }
        notifyDataSetChanged();
    }

    public void clear() {
        synchronized (mLock) {
            mObjects.clear();
        }
        notifyDataSetChanged();
    }

    public void setData(List<MovieModel> data) {
        clear();
        for (MovieModel movie : data) {
            add(movie);
        }
    }

    @Override
    public int getCount() {
        return mObjects.size();
    }

    @Override
    public MovieModel getItem(int position) {
        return mObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;

        if (view == null) {
            view = mInflater.inflate(R.layout.grid_item_movie, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }

        final MovieModel movie = getItem(position);

        viewHolder = (ViewHolder) view.getTag();
        Picasso.with(mContext).load("http://image.tmdb.org/t/p/w342/" + movie.getPoster_path()).into(viewHolder.imageView);
        viewHolder.titleView.setText(movie.getOriginal_title());

        return view;
    }

    public static class ViewHolder {
        public final ImageView imageView;
        public final TextView titleView;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.ivGridItemMovieImage);
            titleView = (TextView) view.findViewById(R.id.tvGridItemMovieTitle);
        }
    }
}
