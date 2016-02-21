package com.udacity.popularmovies2.globals;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Prashant on 12/4/2015.
 */
public class CheckNetwork {


    private static final String TAG = CheckNetwork.class.getSimpleName();

    public static boolean isInternetAvailable(Context context)
    {
        NetworkInfo info = (NetworkInfo) ((ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (info == null)
        {
            //Log.d(TAG,"no internet connection");
            return false;
        }
        else
        {
            if(info.isConnected())
            {
                //Log.d(TAG," internet connection available...");
                return true;
            }
            else
            {
                //Log.d(TAG," internet connection");
                return true;
            }
        }
    }
}
