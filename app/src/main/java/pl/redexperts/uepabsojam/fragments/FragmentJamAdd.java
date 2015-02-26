package pl.redexperts.uepabsojam.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TimePicker;

import java.text.ParseException;
import java.util.Calendar;

import pl.redexperts.uepabsojam.R;
import pl.redexperts.uepabsojam.listeners.ApiResponseListener;
import pl.redexperts.uepabsojam.model.Jam;
import pl.redexperts.utils.DateUtils;
import pl.redexperts.utils.KeyboardUtils;


public class FragmentJamAdd extends Fragment
        implements View.OnClickListener, ApiResponseListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    protected static final String TAG = FragmentJamAdd.class.getSimpleName();
    private long jamId;
    private RelativeLayout cancelButton, saveButton;
    private boolean edit = false;
    private EditText name, description, datetime, street, flatNumber, city, personsNumber;
    private EditText editDate;
    private EditText editTime;
  //  private AbsoListener.OnJamAddListener listener;


//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            listener = (AbsoListener.OnJamAddListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString() + " must implement AbsoListener");
//        }
//    }



    private void handleSave() {

        Jam jam = new Jam();
        jam.setStreet(street.getText().toString());
        jam.setName(name.getText().toString());
        jam.setDescription(description.getText().toString());
        jam.setStreet(street.getText().toString());
        jam.setHouseNumber(flatNumber.getText().toString());
        jam.setCity(city.getText().toString());
        jam.setNeededPeopleNumber(Integer.valueOf(personsNumber.getText().toString()));
        jam.setDate(DateUtils.parseToApiFormat(
                editDate.getText().toString(),
                editTime.getText().toString()));

        //listener.onJamAdd(jam);

        FragmentJams.jams.add(jam);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        KeyboardUtils.hideKeyboard(getActivity());
        getFragmentManager().popBackStack();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setFragmentElements();

        editDate = (EditText) getView().findViewById(R.id.edit_measure_date);
        editTime = (EditText) getView().findViewById(R.id.edit_measure_time);

        editTime.setText(DateUtils.formatAsTime(Calendar.getInstance().getTime()));
        editDate.setText(DateUtils.formatAsDate(Calendar.getInstance().getTime()));

        editTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = editTime.getText().toString();
                TimePickerDialog timePicker;
                try {
                    timePicker = DateUtils.getPickerTime(
                            getActivity(), FragmentJamAdd.this, time);
                    timePicker.show();
                } catch (ParseException e) {
                    Log.w(TAG, e.getMessage());
                }
            }
        });



        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = editDate.getText().toString();
                DatePickerDialog picker;
                try {
                    picker = DateUtils.getPickerDate(
                            getActivity(), FragmentJamAdd.this, date);
                    picker.show();
                } catch (ParseException e) {
                    Log.w(TAG, e.getMessage());
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.save_button:
                handleSave();
                getFragmentManager().popBackStack();
                break;

            case R.id.cancel_button:
                KeyboardUtils.hideKeyboard(getActivity());
                getFragmentManager().popBackStack();
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_jam_edit, container, false);
    }

    @Override
    public void onFailureApiResponse(int statusCode, String response) {
        if (!isVisible()) {
            return;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onSuccessApiResponse(final String method, String response) {


        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getFragmentManager().popBackStack();
            }
        });
    }

    private void setFragmentElements() {
        description = (EditText) getView().findViewById(R.id.jam_description);
        name = (EditText) getView().findViewById(R.id.jam_name);
        street = (EditText) getView().findViewById(R.id.jam_street);
        personsNumber = (EditText) getView().findViewById(R.id.jam_person_number);
        flatNumber = (EditText) getView().findViewById(R.id.jam_address_number);
        city = (EditText) getView().findViewById(R.id.jam_address_city);

        cancelButton = (RelativeLayout) getView().findViewById(R.id.cancel_button);
        saveButton = (RelativeLayout) getView().findViewById(R.id.save_button);

        cancelButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        editDate.setText(DateUtils.formatToUserFormat(dayOfMonth, monthOfYear, year));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        editTime.setText(DateUtils.formatToUserFormat(hourOfDay, minute));
    }
}
