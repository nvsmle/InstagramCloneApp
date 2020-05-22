package neversmile.packag.com.instagramcloneapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUp extends AppCompatActivity {

    private EditText edtEmailSignUp, edtUsernameSignUp, edtPasswordSignUp;
    private Button btnLogIn_SignUp, btnSignUp_SignUp;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        edtEmailSignUp = findViewById(R.id.edtEmailSignUp);
        edtUsernameSignUp = findViewById(R.id.edtUsernameSignUp);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);

        btnLogIn_SignUp = findViewById(R.id.btnLogIn_SignUp);
        btnSignUp_SignUp = findViewById(R.id.btnSignUp_SignUp);

        btnLogIn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignUp.this, LogIn.class);
                startActivity(intent);

            }
        });

        btnSignUp_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser appUser = new ParseUser();

                appUser.setEmail(edtEmailSignUp.getText().toString());
                appUser.setUsername(edtUsernameSignUp.getText().toString());
                appUser.setPassword(edtPasswordSignUp.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {

                        if (e == null) {

                            Toast.makeText(SignUp.this, "Sign Up successfully!", Toast.LENGTH_SHORT).show();

                        } else {

                            Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });

            }
        });


    }
}
