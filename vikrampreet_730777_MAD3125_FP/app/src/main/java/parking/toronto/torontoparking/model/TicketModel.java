package parking.toronto.torontoparking.model;

import android.database.Cursor;

import static parking.toronto.torontoparking.db.DBHelper.CONTACTS_COLUMN_BRAND;
import static parking.toronto.torontoparking.db.DBHelper.CONTACTS_COLUMN_COLOR;
import static parking.toronto.torontoparking.db.DBHelper.CONTACTS_COLUMN_NAME;
import static parking.toronto.torontoparking.db.DBHelper.CONTACTS_COLUMN_PARKING;
import static parking.toronto.torontoparking.db.DBHelper.CONTACTS_COLUMN_PAYMENT;
import static parking.toronto.torontoparking.db.DBHelper.CONTACTS_COLUMN_PRICE;
import static parking.toronto.torontoparking.db.DBHelper.CONTACTS_COLUMN_TIME;
import static parking.toronto.torontoparking.db.DBHelper.CONTACTS_COLUMN_TIMING;

public class TicketModel {

    public String name;
    public String color;
    public String timing;
    public String parking;
    public String payment;
    public String brand;
    public String date;
    public String price;

    public TicketModel(String name, String brand, String color, String time, String lane, String payment) {
        this.name = name;
        this.brand = brand;
        this.color = color;
        this.timing = time;
        this.parking = lane;
        this.payment = payment;
    }

    public TicketModel(Cursor cursor) {

        name = cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_NAME));
        color = cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_COLOR));
        timing = cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_TIMING));
        parking = cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_PARKING));
        payment = cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_PAYMENT));
        brand = cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_BRAND));
        price = cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_PRICE));
        date = cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_TIME));
    }

    public String getAll() {
        return "Vehicle Brand - " + brand + "\n" +
                "Vehicle Name - " + name + "\n" +
                "Vehicle Color - " + color + "\n" +
                "Timing - " + timing + "\n" +
                "Lane - " + parking + "\n" +
                "Payment mode - " + payment + "\n";
    }
}
