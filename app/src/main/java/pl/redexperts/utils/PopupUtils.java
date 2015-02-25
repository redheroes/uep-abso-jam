package pl.redexperts.utils;


import android.content.Context;
import android.view.View;
import android.widget.PopupMenu;

public class PopupUtils {

    public static void showPopup(Context context, View view,
                                 PopupMenu.OnMenuItemClickListener listener , int menuId){
        PopupMenu popup = new PopupMenu(context, view);
        popup.setOnMenuItemClickListener(listener);
        popup.inflate(menuId);
        popup.show();
    }
}
