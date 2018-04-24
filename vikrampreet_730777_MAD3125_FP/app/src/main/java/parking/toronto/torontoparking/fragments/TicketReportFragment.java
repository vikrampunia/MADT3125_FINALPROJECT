package parking.toronto.torontoparking.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import parking.toronto.torontoparking.R;
import parking.toronto.torontoparking.UserManager;
import parking.toronto.torontoparking.model.TicketModel;

public class TicketReportFragment extends Fragment {

    View view;
    ListView lvReport;
    private ArrayList<TicketModel> ticketModels;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_report, container, false);
        load();
        return view;
    }

    private void load() {
        lvReport = view.findViewById(R.id.lvReport);
        ticketModels = UserManager.getInstance().mydbManager.getAllTicket();

        ReportAdapter appsAdapter = new ReportAdapter(ticketModels, getActivity());
        lvReport.setAdapter(appsAdapter);
    }

    private class HolderReport {
        TextView tvAll, tvPrice, tvDate;
    }

    private class ReportAdapter extends ArrayAdapter<TicketModel> {

        ReportAdapter(ArrayList<TicketModel> data, Context context) {
            super(context, R.layout.adapter_ticket, data);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {

            HolderReport viewHolder;

            if (convertView == null) {

                viewHolder = new HolderReport();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.adapter_ticket, parent, false);
                viewHolder.tvAll = convertView.findViewById(R.id.tvAll);
                viewHolder.tvPrice = convertView.findViewById(R.id.tvPrice);
                viewHolder.tvDate = convertView.findViewById(R.id.tvDate);

                convertView.setTag(viewHolder);

            } else {
                viewHolder = (HolderReport) convertView.getTag();
            }

            TicketModel ticketModel = ticketModels.get(position);

            viewHolder.tvDate.setText(ticketModel.date);
            viewHolder.tvPrice.setText("$" + ticketModel.price);

            String allString = ticketModel.getAll();

            viewHolder.tvAll.setText(allString);

            return convertView;
        }

    }//end class

}
