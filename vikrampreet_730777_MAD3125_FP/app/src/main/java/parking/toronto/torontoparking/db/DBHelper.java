package parking.toronto.torontoparking.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import parking.toronto.torontoparking.model.TicketModel;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBNametemp.db";
    public static final String CONTACTS_TABLE_NAME = "ticket";

    public static final String CONTACTS_COLUMN_ID = "id";

    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_COLOR = "color";
    public static final String CONTACTS_COLUMN_TIMING = "timing";
    public static final String CONTACTS_COLUMN_PARKING = "parking";
    public static final String CONTACTS_COLUMN_PAYMENT = "payment";
    public static final String CONTACTS_COLUMN_BRAND = "brand";
    public static final String CONTACTS_COLUMN_PRICE = "price";
    public static final String CONTACTS_COLUMN_TIME = "timeS";
    private HashMap hp;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table ticket " +
                        "(id integer primary key, name text,color text,timing text, parking text,payment text,brand text,timeS text,price text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ticket");
        onCreate(db);
    }

    public boolean insertTicket(TicketModel ticketModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(CONTACTS_COLUMN_NAME, ticketModel.name);
        contentValues.put(CONTACTS_COLUMN_COLOR, ticketModel.color);
        contentValues.put(CONTACTS_COLUMN_TIMING, ticketModel.timing);
        contentValues.put(CONTACTS_COLUMN_PARKING, ticketModel.parking);
        contentValues.put(CONTACTS_COLUMN_PAYMENT, ticketModel.payment);
        contentValues.put(CONTACTS_COLUMN_BRAND, ticketModel.brand);

        contentValues.put(CONTACTS_COLUMN_PRICE, "145");
        Date date = new Date();
        contentValues.put(CONTACTS_COLUMN_TIME, date.toString());

        db.insert(CONTACTS_TABLE_NAME, null, contentValues);
        return true;
    }


    public ArrayList<TicketModel> getAllTicket() {
        ArrayList<TicketModel> array_list = new ArrayList<>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + CONTACTS_TABLE_NAME, null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            TicketModel ticketModel = new TicketModel(res);
            array_list.add(ticketModel);
            res.moveToNext();
        }
        res.close();

        return array_list;
    }

}
