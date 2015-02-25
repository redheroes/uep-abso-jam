package pl.redexperts.utils;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import pl.redexperts.uepabsojam.LoginActivity;
import pl.redexperts.uepabsojam.MainActivity;
import pl.redexperts.uepabsojam.R;


public class ProgressDialogUtils {

    private static ProgressDialog progressDialog;
    private static int theme = R.style.ProgressDialog;
    private static int layout = R.layout.loader;

    public static void setIconDialog(final Activity activity) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog = new ProgressDialog(activity, theme);
                progressDialog.setCancelable(false);
                progressDialog.show();
                progressDialog.setContentView(layout);
            }
        });

    }

    public static void dissmissIconDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}