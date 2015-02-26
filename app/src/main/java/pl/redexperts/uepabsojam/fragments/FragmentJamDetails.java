package pl.redexperts.uepabsojam.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import pl.redexperts.uepabsojam.R;
import pl.redexperts.utils.FragmentHelper;


public class FragmentJamDetails extends Fragment implements View.OnClickListener{

    private TextView jamTitle;
    private TextView jamWhen;
    private TextView jamWhere;
    private TextView jamUsers;
    private TextView jamDetails;
    private RelativeLayout usersButton;
    private RelativeLayout joinButton;

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
        jamTitle.setText(title);
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

        joinButton.setOnClickListener(this);
        usersButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel_button :
                FragmentHelper.showFragment(getActivity(), R.id.content, new FragmentUsersList(), true);
                break;
            case R.id.save_button :
                FragmentHelper.showFragment(getActivity(), R.id.content, new FragmentUsersList(), true);
                break;
         }
    }
}
