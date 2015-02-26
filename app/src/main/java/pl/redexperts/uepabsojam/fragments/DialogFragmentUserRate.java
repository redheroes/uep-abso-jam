package pl.redexperts.uepabsojam.fragments;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pl.redexperts.uepabsojam.R;


public class DialogFragmentUserRate extends DialogFragment {


    private Button buttonAccept;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle("Ocena użytkownika");
        View view = inflater.inflate(R.layout.dialog_fragment_user_rate, container, false);
        Button button = (Button) view.findViewById(R.id.rate_save_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }


}
