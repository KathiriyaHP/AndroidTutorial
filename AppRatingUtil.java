package com.va.languagetranslator.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.va.languagetranslator.R;


/**
 * Created by Harshad on 8/2/2016.
 */
public class AppRatingUtil1
{

    public static void app_launched(Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences(""+mContext.getResources().getString(R.string.app_name), 0);

        if (prefs.getBoolean("dontshowagain", false)) { return ; }
        SharedPreferences.Editor editor = prefs.edit();

        // Increment launch counter
        int launch_count = prefs.getInt("launch_count", 0) + 1;
        editor.putInt("launch_count", launch_count);

        // Get date of first launch
        Long date_firstLaunch = prefs.getLong("date_firstlaunch", 0);
        if (date_firstLaunch == 0) {
            date_firstLaunch = System.currentTimeMillis();
            editor.putLong("date_firstlaunch", date_firstLaunch);
        }

        // Wait at least n days before opening

        if (launch_count > 4) {
            showRateDialog(mContext, editor);
            launch_count = 0;
            editor.putInt("launch_count", launch_count);
        }

        if (System.currentTimeMillis() >= date_firstLaunch +
                (3 * 24 * 60 * 60 * 1000)) {
            showRateDialog(mContext, editor);
        }
        editor.commit();
    }

    public static void showRateDialog(final Context mContext, final SharedPreferences.Editor editor) {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setTitle("Rate " + mContext.getResources().getString(R.string.app_name));
        dialog.setContentView(R.layout.rating_dialog);

        Button alert_rating = (Button)dialog.findViewById(R.id.btnRating);
        Button alert_remind = (Button)dialog.findViewById(R.id.btnYes);
        Button alert_no = (Button)dialog.findViewById(R.id.btnNo);

        alert_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" +mContext.getPackageName())));
                } catch (android.content.ActivityNotFoundException anfe) {
                    mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + mContext.getPackageName())));
                }

            }
        });
        alert_remind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        alert_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editor != null) {
                    editor.putBoolean("dontshowagain", true);
                    editor.commit();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
