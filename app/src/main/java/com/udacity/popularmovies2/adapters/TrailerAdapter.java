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
import com.udacity.popularmovies2.model.TrailerModel;

import java.util.List;

public class TrailerAdapter extends BaseAdapter {

    private final Context mContext;
    private final LayoutInflater mInflater;
    private final TrailerModel mLock = new TrailerModel();

    private List<TrailerModel> mObjects;

    public TrailerAdapter(Context context, List<TrailerModel> objects) {
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mObjects = objects;
    }

    public Context getContext() {
        return mContext;
    }

    public void add(TrailerModel object) {
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

    @Override
    public int getCount() {
        return mObjects.size();
    }

    @Override
    public TrailerModel getItem(int position) {
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
            view = mInflater.inflate(R.layout.list_movie_trailer, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }

        final TrailerModel trailer = getItem(position);

        viewHolder = (ViewHolder) view.getTag();
        Picasso.with(mContext).load("http://img.youtube.com/vi/" + trailer.getKey() + "/0.jpg").into(viewHolder.imageView);

        viewHolder.nameView.setText(trailer.getName());

        return view;
    }

    public static class ViewHolder {
        public final ImageView imageView;
        public final TextView nameView;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.ivMovieTrailerImage);
            nameView = (TextView) view.findViewById(R.id.tvMovieTrailerName);
        }
    }

}
