package pl.redexperts.uepabsojam.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import pl.redexperts.uepabsojam.R;
import pl.redexperts.uepabsojam.model.Jam;
import pl.redexperts.utils.FragmentHelper;


public class FragmentJamDetails extends Fragment implements View.OnClickListener {

    private TextView jamTitle;
    private TextView jamWhen;
    private TextView jamWhere;
    private TextView jamUsers;
    private TextView jamDetails;
    private RelativeLayout usersButton;
    private RelativeLayout joinButton;
    private FloatingActionButton rateButton;
    private boolean organized = false;
    //    #### Params
    private String title = "Build your awesome app. The best JAM ever.";
    private String when = "12.05.2015 12:55";
    private String where = "Poznań, al.Niepodległości 10";
    private String users = "15/20";
    private String details = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum";


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentJamDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentJamDetails newInstance(String param1, String param2) {
        FragmentJamDetails fragment = new FragmentJamDetails();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_jam_details, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        MaterialNavigationDrawer activity = (MaterialNavigationDrawer) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Szczegóły Jamu");


        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        jamTitle = (TextView) view.findViewById(R.id.jamTitle);

        jamWhen = (TextView) view.findViewById(R.id.jamWhenValue);
        jamWhen.setText(when);
        jamWhere = (TextView) view.findViewById(R.id.jamWhereValue);
        jamWhere.setText(where);
        jamUsers = (TextView) view.findViewById(R.id.jamUsersValue);
        jamUsers.setText(users);
        jamDetails = (TextView) view.findViewById(R.id.jamDelails);
        jamDetails.setText(details);

        joinButton = (RelativeLayout) getView().findViewById(R.id.cancel_button);
        usersButton = (RelativeLayout) getView().findViewById(R.id.save_button);
        rateButton = (com.getbase.floatingactionbutton.FloatingActionButton) getView().findViewById(R.id.rate_jam_button);

        joinButton.setOnClickListener(this);
        usersButton.setOnClickListener(this);
        rateButton.setOnClickListener(this);

        Bundle bundle = getArguments();

        if (bundle != null && bundle.getString("jam") != null) {
            jamTitle.setText(bundle.getString("jam"));

        }
        if (bundle != null && bundle.getSerializable("key") != null) {
            jamTitle.setText(bundle.getString("key"));
        }
        if (bundle != null && bundle.getBoolean("organized")) {
            organized = true;
        }
        if (bundle != null && bundle.getBoolean("nie") == true) {
            View view1 = getView().findViewById(R.id.buttons);
            if (view1 != null) {
                view1.setVisibility(View.GONE);
            }
        } else {
            if (bundle != null) {
                fillLayoutUsingDataFromBundle(bundle);
            }

        }

    }

    private void fillLayoutUsingDataFromBundle(Bundle bundle) {
        if (bundle != null) {
            Jam jam = (Jam) bundle.getSerializable("key");
            if (jam != null) {
                if (jam.getName() != null) {
                    jamTitle.setText(jam.getName());
                }

                String where = "";

                if (jam.getCity() != null) {
                    where = where.concat(jam.getCity() + ", ");
                }

                if (jam.getStreet() != null) {
                    where = where.concat(jam.getCity() + ", ");
                }

                if (jam.getHouseNumber() != null) {
                    where = where.concat(jam.getHouseNumber());
                }
                jamWhere.setText(where);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");

                if (jam.getDescription() != null) {
                    jamDetails.setText(jam.getDescription());
                }

                if (jam.getNeededPeopleNumber() != null && jam.getCurrentPeopleNumber() != null) {
                    jamUsers.setText(jam.getCurrentPeopleNumber() + "/" + jam.getNeededPeopleNumber());
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_button:
                if (organized){
                    FragmentHelper.showFragment(getActivity(), R.id.content, new FragmentUsersList(), true);
                }
                else {
                    FragmentHelper.showFragment(getActivity(), R.id.content, new FragmentJamUsers(false), true);
                }
                break;
            case R.id.save_button:
                if (organized){
                    FragmentHelper.showFragment(getActivity(), R.id.content, new FragmentUsersList(), true);
                }
                else {
                    FragmentHelper.showFragment(getActivity(), R.id.content, new FragmentJamUsers(false), true);
                }
                break;
            case R.id.rate_jam_button:
                System.out.println("adfag");
                FragmentManager fm = getActivity().getSupportFragmentManager();
                DialogFragmentRate dialogRate = new DialogFragmentRate();
                dialogRate.show(fm, "dialog_fragment_add_meeting");
                break;
        }
    }
}
