package pl.redexperts.uepabsojam.fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import pl.redexperts.uepabsojam.R;
import pl.redexperts.utils.FragmentHelper;
import pl.redexperts.utils.RoundImage;


public class FragmentUsersInfo extends Fragment implements View.OnClickListener{

    private Button jam1;
    private Button jam2;
    private Button jam3;
    private Button jam4;
    private ImageView imageView;
    private RoundImage roundedImage;
    private RelativeLayout usersButton;
    private RelativeLayout joinButton;
    private FloatingActionButton rateButton;

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
    public static FragmentUsersInfo newInstance(String param1, String param2) {
        FragmentUsersInfo fragment = new FragmentUsersInfo();
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
        View view = inflater.inflate(R.layout.fragment_user_details, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        MaterialNavigationDrawer activity = (MaterialNavigationDrawer) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Szczegóły uczestnika");

        imageView = (ImageView) view.findViewById(R.id.user_icon);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.michal_bielak);
        roundedImage = new RoundImage(bm);
        imageView.setImageDrawable(roundedImage);

        jam1 = (Button) view.findViewById(R.id.jam1);
        jam2 = (Button) view.findViewById(R.id.jam2);
        jam3 = (Button) view.findViewById(R.id.jam3);
        jam4 = (Button) view.findViewById(R.id.jam4);

        jam1.setOnClickListener(this);
        jam2.setOnClickListener(this);
        jam3.setOnClickListener(this);
        jam4.setOnClickListener(this);

        rateButton = (com.getbase.floatingactionbutton.FloatingActionButton) view.findViewById(R.id.rate_jam_button);

        rateButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        FragmentJamDetails fragmentJamDetails = new FragmentJamDetails();
        Bundle bundle = new Bundle();
        switch (v.getId()){

            case R.id.cancel_button :
                FragmentHelper.showFragment(getActivity(), R.id.content, new FragmentUsersList(), true);
                break;
            case R.id.save_button :
                FragmentHelper.showFragment(getActivity(), R.id.content, new FragmentUsersList(), true);
                break;

            case R.id.jam1 :
                bundle.putString("jam", jam1.getText().toString());
                bundle.putBoolean("nie", true);
                fragmentJamDetails.setArguments(bundle);
                FragmentHelper.showFragment(getActivity(), R.id.content, fragmentJamDetails, true);
                break;
            case R.id.jam2 :
                bundle.putString("jam", jam2.getText().toString());
                bundle.putBoolean("nie", true);
                fragmentJamDetails.setArguments(bundle);
                FragmentHelper.showFragment(getActivity(), R.id.content, fragmentJamDetails, true);
                break;
            case R.id.jam3 :
                bundle.putString("jam", jam3.getText().toString());
                bundle.putBoolean("nie", true);
                fragmentJamDetails.setArguments(bundle);
                FragmentHelper.showFragment(getActivity(), R.id.content, fragmentJamDetails, true);
                break;
            case R.id.jam4 :
                bundle.putString("jam", jam4.getText().toString());
                bundle.putBoolean("nie", true);
                fragmentJamDetails.setArguments(bundle);
                FragmentHelper.showFragment(getActivity(), R.id.content, fragmentJamDetails, true);
                break;
            case R.id.rate_jam_button:
                System.out.println("adfag");
                FragmentManager fm = getActivity().getSupportFragmentManager();
                DialogFragmentUserRate dialogRate = new DialogFragmentUserRate();
                dialogRate.show(fm, "dialog_fragment_add_meeting");
                break;
         }
    }
}
