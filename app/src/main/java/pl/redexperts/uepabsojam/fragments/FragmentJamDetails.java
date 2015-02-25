package pl.redexperts.uepabsojam.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pl.redexperts.uepabsojam.R;


public class FragmentJamDetails extends Fragment {

    private TextView jamTitle;
    private TextView jamWhen;
    private TextView jamWhere;
    private TextView jamUsers;
    private TextView jamDetails;

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

    }


}
