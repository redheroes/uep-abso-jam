package pl.redexperts.uepabsojam.fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.util.Calendar;

import pl.redexperts.uepabsojam.R;
import pl.redexperts.utils.DateUtils;
import pl.redexperts.utils.FragmentHelper;


public class FragmentSettings extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private TextView jamTitle;
    private TextView jamWhen;
    private TextView jamWhere;
    private TextView jamUsers;
    private TextView jamDetails;
    private RelativeLayout saveButton;
    private RelativeLayout cancelButton;
    private TextView textDate;


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
    public static FragmentSettings newInstance(String param1, String param2) {
        FragmentSettings fragment = new FragmentSettings();
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
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        saveButton = (RelativeLayout) getView().findViewById(R.id.save_button);
        cancelButton = (RelativeLayout) getView().findViewById(R.id.cancel_button);

        saveButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

        textDate = (TextView) getView().findViewById(R.id.last_examination_date);
        textDate.setText(DateUtils.formatAsDate(Calendar.getInstance().getTime()));

        textDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = textDate.getText().toString();
                DatePickerDialog picker;
                try {
                    picker = DateUtils.getPickerDate(
                            getActivity(), FragmentSettings.this, date);
                    picker.show();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_button:
                FragmentHelper.showFragment(getActivity(), R.id.content, new FragmentJams(), false);
                break;
            case R.id.save_button:
                FragmentHelper.showFragment(getActivity(), R.id.content, new FragmentJams(), false);
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        textDate.setText(DateUtils.formatToUserFormat(dayOfMonth, monthOfYear, year));
    }
}
