package pl.redexperts.uepabsojam.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupMenu;

import java.util.ArrayList;

import pl.redexperts.uepabsojam.adapters.AdapterJams;
import pl.redexperts.uepabsojam.R;
import pl.redexperts.uepabsojam.listeners.ApiResponseListener;
import pl.redexperts.uepabsojam.listeners.OnArrayListContextMenuListener;
import pl.redexperts.uepabsojam.model.Jam;
import pl.redexperts.utils.FragmentHelper;
import pl.redexperts.utils.PopupUtils;

public class FragmentJams extends Fragment implements View.OnClickListener,
        PopupMenu.OnMenuItemClickListener, ApiResponseListener, OnArrayListContextMenuListener {


    private static final String TAG = FragmentJams.class.getSimpleName();

    private AdapterJams adapter;
    private Jam jam;
    private ArrayList<Jam> jams = new ArrayList<>();
    public final static String KEY_BUNDLE = "jam";

    public Bundle getJamBundle() {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_BUNDLE, jam);
        return bundle;
    }

    public void getJamsFromServer() {
        //TODO: add mock jams
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.add_jam_button:

                //TODO: start edit jam fragment
                break;


        }
    }

    @Override
    public void onContextMenuClicked(int position, View view) {
        jam = adapter.getItem(position);

        PopupUtils.showPopup(getActivity(), view.findViewById(R.id.jam_menu_button),
                this, R.menu.jams_context_menu);
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
        return inflater.inflate(R.layout.fragment_jam_list, container, false);
    }

    @Override
    public void onFailureApiResponse(int statusCode, String response) {
        if (!isVisible()) {
            return;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.jam_details:
                FragmentHelper.showFragment(getActivity(), R.id.container,
                        new FragmentJams(), true);

                return true;

            case R.id.jam_take_apart:
                //TODO: take apart action
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

    @Override
    public void onSuccessApiResponse(final String method, final String response) {
        if (!isVisible()) {
            return;
        }

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updateUi(method, response);
            }
        });
    }

    private void setFragmentElements() {
        adapter = new AdapterJams(getActivity(), R.id.jams_list_container, jams);
        adapter.setOnContextMenuClickListener(this);

        getView().findViewById(R.id.add_jam_button).setOnClickListener(this);
        ListView listView = ((ListView) getView().findViewById(R.id.jams_list_container));
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
    }

    private void updateUi(String method, String response) {
        adapter.clear();
        adapter.addAll(jams);
        adapter.notifyDataSetChanged();
    }
}
