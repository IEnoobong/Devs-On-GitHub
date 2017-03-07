package co.enoobong.devsongithub.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;

/**
 * Created by owner on 6/3/2017.
 */

public class Utils {
    public static final String DEVELOPERS = "developers";
    //API Query Parameters
    public static final String LANGUAGE = "Java";
    public static final String LOCATION = "Lagos";

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static AlertDialog.Builder showDialog(Context context, int icon, int message) {
        return new AlertDialog.Builder(context)
                .setMessage(message)
                .setIcon(icon);
    }
}
