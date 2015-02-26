package pl.redexperts.uepabsojam.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import pl.redexperts.uepabsojam.R;
import pl.redexperts.uepabsojam.listeners.ApiResponseListener;
import pl.redexperts.uepabsojam.model.Jam;
import pl.redexperts.utils.KeyboardUtils;


public class FragmentJamAdd extends Fragment
        implements View.OnClickListener, ApiResponseListener {

    protected static final String TAG = FragmentJamAdd.class.getSimpleName();
    private long jamId;
    private RelativeLayout cancelButton, saveButton;
    private boolean edit = false;
    private EditText name, description, datetime, street, flatNumber, city, personsNumber;

    private void handleSave() {

        Jam jam = new Jam();
        jam.setStreet(street.getText().toString());

        KeyboardUtils.hideKeyboard(getActivity());



    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setFragmentElements();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.save_button:
                handleSave();
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
        datetime = (EditText) getView().findViewById(R.id.jam_date_time);
        street = (EditText) getView().findViewById(R.id.jam_street);
        personsNumber = (EditText) getView().findViewById(R.id.jam_person_number);
        flatNumber = (EditText) getView().findViewById(R.id.jam_address_number);
        city = (EditText) getView().findViewById(R.id.jam_address_city);

        cancelButton = (RelativeLayout) getView().findViewById(R.id.cancel_button);
        saveButton = (RelativeLayout) getView().findViewById(R.id.save_button);

        cancelButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);
    }
}
