package pl.redexperts.uepabsojam.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

import pl.redexperts.uepabsojam.R;
import pl.redexperts.uepabsojam.adapters.AdapterMyJams;
import pl.redexperts.uepabsojam.listeners.OnArrayListContextMenuListener;
import pl.redexperts.utils.FragmentHelper;
import pl.redexperts.utils.PopupUtils;

public class FragmentUsersList extends Fragment implements PopupMenu.OnMenuItemClickListener,
        OnArrayListContextMenuListener {


    private static final String TAG = FragmentUsersList.class.getSimpleName();

    private AdapterMyJams adapter;
    private String jam;
    private ArrayList<String> jamsFuture;
    private ArrayList<String> jamsGone;
    private ArrayList<String> jamsOrganized;
    public final static String KEY_BUNDLE = "jam";

    public void getJamsFromServer() {
        //TODO: add mock jams
    }



    @Override
    public void onContextMenuClicked(int position, View view) {
        jam = adapter.getItem(position);

        PopupUtils.showPopup(getActivity(), view.findViewById(R.id.jam_menu_button),
                this, R.menu.my_jams_context_menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.jams_context_menu, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_users_list, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        jamsFuture = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.jams_future)));
        jamsGone = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.jams_gone)));
        jamsOrganized = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.jams_organized)));
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.jam_details:
                //TODO: show details
                FragmentHelper.showFragment(getActivity(), R.id.content, new FragmentJamDetails(), true);
                return true;

            default:
                return false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        setFragmentElements();

        getJamsFromServer();
    }

    private void setFragmentElements() {
        adapter = new AdapterMyJams(getActivity(), R.id.jams_list_container, jamsFuture);
        adapter.setOnContextMenuClickListener(this);

        ArrayAdapter adapterJams = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.jams_filter, R.layout.spinner_text_view);

        adapterJams.setDropDownViewResource(R.layout.spinner_text_view);


        ListView listView = ((ListView) getView().findViewById(R.id.jams_list_container));
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
    }

    private void updateUi(ArrayList<String> jams) {
        adapter.clear();
        adapter.addAll(jams);
        adapter.notifyDataSetChanged();
    }
}
