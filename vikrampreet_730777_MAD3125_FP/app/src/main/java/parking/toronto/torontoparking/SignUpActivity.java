package parking.toronto.torontoparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static parking.toronto.torontoparking.LoginActivity.isValidEmail;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etEmail, etPassword, etFullName;
    Button btnCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etFullName = findViewById(R.id.etFullName);

        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        btnCreateAccount.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCreateAccount:
                createAccount();
                break;
        }
    }

    private void createAccount() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String fullName = etFullName.getText().toString();
        String error = "", name = "";

        if (!fullName.isEmpty()) {
            if (isValidEmail(email)) {
                if (!password.isEmpty()) {
                    error = "";
                    name = fullName;
                } else {
                    error = "Password is empty";
                }
            } else {
                error = "Email is not valid";
            }
        } else {
            error = "Full Name is not valid";
        }


        //if error is not empty
        if (!error.isEmpty()) {
            //Show alert
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        } else {
            etEmail.setText("");
            etPassword.setText("");
            etFullName.setText("");

            UserManager.getInstance().userEmail = email;
            UserManager.getInstance().userName = name;

            Toast.makeText(this, "You are Created account successfully", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, WelcomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}
