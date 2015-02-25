package pl.redexperts.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentHelper {


    public static void showFragment(FragmentActivity activity,
                                    int container, Fragment f, boolean addToBackstack) {

        String tag = f.getClass().getSimpleName();

        if (f.getArguments() != null) {
            tag = tag + "_" + String.valueOf(f.getArguments().hashCode());
        }

        FragmentManager fm = activity.getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();

        Fragment fragment = fm.findFragmentByTag(tag);

        boolean doCommit = false;

        if (null != fragment) {

            if (!fragment.isVisible()) {
                ft.replace(container, fragment, tag);
                doCommit = true;
            }

        } else {
            ft.replace(container, f, tag);
            doCommit = true;
        }

        if (addToBackstack) {
            ft.addToBackStack(null);
        }

        if (doCommit) {
            ft.commit();
        }
    }
}
