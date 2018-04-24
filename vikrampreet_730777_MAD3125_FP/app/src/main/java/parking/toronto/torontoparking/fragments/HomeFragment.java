package parking.toronto.torontoparking.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import parking.toronto.torontoparking.R;
import parking.toronto.torontoparking.UserManager;

public class HomeFragment extends Fragment {

    View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        loadDate();
        return view;
    }

    private void loadDate() {

        int size = UserManager.getInstance().mydbManager.getAllTicket().size();

        Button btnCount = view.findViewById(R.id.btnCount);
        btnCount.setText("" + size);
    }


}
