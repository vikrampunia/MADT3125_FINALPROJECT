package parking.toronto.torontoparking;

import parking.toronto.torontoparking.db.DBHelper;

public class UserManager {
    private static final UserManager ourInstance = new UserManager();

    public static UserManager getInstance() {
        return ourInstance;
    }

    private UserManager() {
    }

    public String userEmail;
    public String userName;
    public DBHelper mydbManager;

}
