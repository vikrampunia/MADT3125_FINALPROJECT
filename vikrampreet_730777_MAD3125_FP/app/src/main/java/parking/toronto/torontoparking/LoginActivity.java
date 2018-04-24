package parking.toronto.torontoparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etEmail, etPassword;
    Button btnLogin, btnCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);

        btnLogin.setOnClickListener(this);
        btnCreateAccount.setOnClickListener(this);

        //Only for testing
        etEmail.setText("admin@tparking.com");
        etPassword.setText("1234");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                login();
                break;
            case R.id.btnCreateAccount:
                //Open sign up page
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                break;
        }
    }

    private void login() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String error = "", name = "";

        if (isValidEmail(email)) {
            if (!password.isEmpty()) {
                if (email.equals("admin@tparking.com") && password.equals("1234")) {
                    Log.d("MyTag", "You have been login successfully");
                    name = "admin";
                } else if (email.equals("support@tparking.com") && password.equals("1234")) {
                    Log.d("MyTag", "You have been login successfully");
                    name = "support";
                } else if (email.equals("sheetal@tparking.com") && password.equals("1234")) {
                    Log.d("MyTag", "You have been login successfully");
                    name = "karamjeet";
                } else if (email.equals("navdeep@tparking.com") && password.equals("1234")) {
                    Log.d("MyTag", "You have been login successfully");
                    name = "taranjeet";
                } else {
                    error = "Email or password is incorrect ";
                }
            } else {
                error = "Password is empty";
            }
        } else {
            error = "Email is not valid";
        }


        //if error is not empty
        if (!error.isEmpty()) {
            //Show alert
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        } else {
            etEmail.setText("");
            etPassword.setText("");
            Toast.makeText(this, "You are login successfully", Toast.LENGTH_SHORT).show();

            UserManager.getInstance().userEmail = email;
            UserManager.getInstance().userName = name;

            Intent intent = new Intent(this, WelcomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
