package parking.toronto.torontoparking.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import parking.toronto.torontoparking.R;
import parking.toronto.torontoparking.UserManager;
import parking.toronto.torontoparking.WelcomeActivity;
import parking.toronto.torontoparking.model.TicketModel;

public class AddTicketFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    View view;
    Spinner spinnerColor, spinnerTiming, spinnerParking, spinnerPayment;
    EditText etVName, etVBrand;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_ticket, container, false);
        load();
        return view;
    }

    private void load() {

        if (getActivity() == null)
            return;

        view.findViewById(R.id.btnAddTicket).setOnClickListener(onClickListener);

        etVName = view.findViewById(R.id.etVName);
        etVBrand = view.findViewById(R.id.etVBrand);
        TextView tvDate = view.findViewById(R.id.tvDate);
        tvDate.setText((new Date()).toString());

        spinnerColor = view.findViewById(R.id.spinnerColor);
        spinnerColor.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<>();
        categories.add("RED");
        categories.add("GREEN");
        categories.add("WHITE");
        categories.add("BLUE");
        categories.add("BLACK");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColor.setAdapter(dataAdapter);


        spinnerTiming = view.findViewById(R.id.spinnerTiming);
        spinnerTiming.setOnItemSelectedListener(this);
        List<String> times = new ArrayList<>();
        times.add("1 hr");
        times.add("2 hr");
        times.add("3 hr");
        times.add("End day");
        ArrayAdapter<String> dataAdapterTiming = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, times);
        dataAdapterTiming.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTiming.setAdapter(dataAdapterTiming);

        spinnerParking = view.findViewById(R.id.spinnerParking);
        spinnerParking.setOnItemSelectedListener(this);
        List<String> parkingdata = new ArrayList<>();
        parkingdata.add("P1");
        parkingdata.add("P2");
        parkingdata.add("P3");
        parkingdata.add("P4");
        ArrayAdapter<String> dataAdapterParking = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, parkingdata);
        dataAdapterParking.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerParking.setAdapter(dataAdapterParking);

        spinnerPayment = view.findViewById(R.id.spinnerPayment);
        spinnerPayment.setOnItemSelectedListener(this);
        List<String> paymentData = new ArrayList<>();
        paymentData.add("Cash");
        paymentData.add("Credit card");
        paymentData.add("Online");
        ArrayAdapter<String> dataAdapterPaymentData = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, paymentData);
        dataAdapterPaymentData.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPayment.setAdapter(dataAdapterPaymentData);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String name = etVName.getText().toString();
            String brand = etVBrand.getText().toString();
            String error = "";

            if (!name.isEmpty() && !brand.isEmpty()) {
                etVName.setText("");
                etVBrand.setText("");

                saveTicket(name, brand);
            } else {
                error = "Please fill both brand and name";
            }

            //if error is not empty
            if (!error.isEmpty()) {
                //Show alert
                Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void saveTicket(String name, String brand) {
        if (getActivity() == null)
            return;

        String color = (String) spinnerColor.getItemAtPosition(spinnerColor.getSelectedItemPosition());
        String time = (String) spinnerTiming.getItemAtPosition(spinnerTiming.getSelectedItemPosition());
        String lane = (String) spinnerParking.getItemAtPosition(spinnerParking.getSelectedItemPosition());
        String payment = (String) spinnerPayment.getItemAtPosition(spinnerPayment.getSelectedItemPosition());

        TicketModel ticketModel = new TicketModel(name, brand, color, time, lane, payment);

        if (UserManager.getInstance().mydbManager.insertTicket(ticketModel)) {
            Toast.makeText(getActivity(), "Ticket added successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Ticket added successfully", Toast.LENGTH_SHORT).show();
        }

        if (getActivity() instanceof WelcomeActivity) {
            WelcomeActivity welcomeActivity = (WelcomeActivity) getActivity();
            welcomeActivity.openMainScreen();
        }
    }
}

