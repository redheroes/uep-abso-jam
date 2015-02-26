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
import android.widget.ListView;
import android.widget.PopupMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import pl.redexperts.uepabsojam.R;
import pl.redexperts.uepabsojam.adapters.AdapterJams;
import pl.redexperts.uepabsojam.listeners.AbsoListener;
import pl.redexperts.uepabsojam.listeners.ApiResponseListener;
import pl.redexperts.uepabsojam.listeners.OnArrayListContextMenuListener;
import pl.redexperts.uepabsojam.model.Jam;
import pl.redexperts.utils.FragmentHelper;
import pl.redexperts.utils.PopupUtils;

public class FragmentJams extends Fragment implements View.OnClickListener,
        PopupMenu.OnMenuItemClickListener, ApiResponseListener, OnArrayListContextMenuListener, AbsoListener.OnJamAddListener {


    private static final String TAG = FragmentJams.class.getSimpleName();

    private AdapterJams adapter;
    private Jam jam;
    public static ArrayList<Jam> jams;
    public boolean loaded = false;



    public void getJamsFromServer() {

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!loaded){
            jams = new ArrayList<Jam>(){{add(new Jam(1L, "Sprzedarz piłeczek tenisowych", 5, 3, new Date(121212)));
                add(new Jam(1L, "Sprzedarz piłeczek tenisowych", 5, 3, new Date(6544454)));
                add(new Jam(1L, "Stworzenie nowej metodologii we wdrażaniu procesów w firmach.", 5, 3, new Date(324324)));
                add(new Jam(1L, "Przeniesienie produkcji do Chin, bo tutaj za drogo. Zaplanowanie całości przedsięwzięcia i koordynacja.", 5, 3, new Date(121212)));
                add(new Jam(1L, "Wyimaginowany przyjaciel, jak z tym żyć.", 5, 3, new Date(5664654)));
                add(new Jam(1L, "Kajakiem przez świat, co zabrać ze sobą prócz kajaka?", 5, 3, new Date(65436435)));
                add(new Jam(1L, "Pomóż uratować lasy deszczone.", 5, 3, new Date(265436526)));
                add(new Jam(1L, "Build your awesome app. The best JAM ever.", 5, 3, new Date(121212)));
                add(new Jam(1L, "Design thinking. Stwórz z nami nasz nowy produkt - WBK", 5, 3, new Date(523626)));
                add(new Jam(1L, "Stwórzmy scenariusz gry planszowej, mam zarys pomysłu.", 5, 3, new Date(5432553)));
                add(new Jam(1L, "Pomóż uratować lasy deszczone.", 5, 3, new Date(64577775)));
                add(new Jam(1L, "Przeniesienie produkcji do Chin, bo tutaj za drogo. Zaplanowanie całości przedsięwzięcia i koordynacja.", 5, 3, new Date(1275471212)));
                add(new Jam(1L, "Stwórzmy scenariusz gry planszowej, mam zarys pomysłu.", 5, 3, new Date(257444444)));
            }};
            loaded = true;
        }
        if (jams.size() > 0) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.clear();
                    adapter.addAll(jams);
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }



    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.add_jam_button:

                FragmentHelper.showFragment(getActivity(), R.id.content, new FragmentJamAdd(), true);
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
        jams = new ArrayList<Jam>();

        return inflater.inflate(R.layout.fragment_jam_list, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

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
                Fragment fragment = new FragmentJamDetails();
                Bundle bundle = new Bundle();
                bundle.putSerializable("key", jam);
                fragment.setArguments(bundle);
                FragmentHelper.showFragment(getActivity(), R.id.content,fragment
                        , true);

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

    @Override
    public void onJamAdd(final Jam jam) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.add(jam);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
