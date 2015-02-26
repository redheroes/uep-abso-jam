package pl.redexperts.uepabsojam;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import pl.redexperts.uepabsojam.fragments.FragmentJams;
import pl.redexperts.uepabsojam.fragments.FragmentMyJams;
import pl.redexperts.uepabsojam.fragments.FragmentSettings;
import pl.redexperts.uepabsojam.model.Jam;


public class MainActivity extends MaterialNavigationDrawer {


    private Menu contextMenu;
    private boolean slidingMenuDisabled = false;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void init(Bundle bundle) {

        MaterialAccount account = new MaterialAccount
                (this.getResources(),"Michał Bielak","bielak.mastah@gigant.pro",R.drawable.michal_bielak,R.drawable.wallpaper);
        addAccount(account);


        int color = getResources().getColor(R.color.main_color);
        int white = getResources().getColor(android.R.color.white);



        setUserEmailTextColor(white);
        setUsernameTextColor(white);
        // create sections
        setDrawerBackgroundColor(getResources().getColor(android.R.color.white));
        addSection(newSection("Lista Jamów", R.drawable.ic_format_list_numbers, new FragmentJams()));
        addSection(newSection("Moje Jamy", R.drawable.ic_playlist_plus, new FragmentMyJams()));
        addSection(newSection("Ludzie", R.drawable.ic_human_male_female, new FragmentJams()));
        addSection(newSection("Ustawienia", R.drawable.ic_settings, new FragmentSettings()));
        getToolbar().setTitleTextColor(color);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu contextMenu) {
        if (slidingMenuDisabled) {
            return super.onCreateOptionsMenu(contextMenu);
        }
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, contextMenu);
        this.contextMenu = contextMenu;
        supportInvalidateOptionsMenu();
        return super.onCreateOptionsMenu(contextMenu);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((keyCode == KeyEvent.KEYCODE_MENU)) {
                contextMenu.performIdentifierAction(R.id.actionbar_context_menu, 0);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.logoutButton:
                redirectToLoginActivity();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void redirectToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
