package neversmile.packag.com.instagramcloneapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class LogIn extends AppCompatActivity {

    private EditText edtEmailLogIn, edtPasswordLogIn;
    private Button btnLogIn_LogIn, btnSignUp_LogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        edtEmailLogIn = findViewById(R.id.edtEmailLogIn);
        edtPasswordLogIn = findViewById(R.id.edtPasswordLogIn);

        btnLogIn_LogIn = findViewById(R.id.btnLogIn_LogIn);
        btnSignUp_LogIn = findViewById(R.id.btnSignUp_LogIn);

        btnSignUp_LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        btnLogIn_LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseQuery<ParseUser> queryUser = ParseUser.getQuery();

                queryUser.whereEqualTo("email", edtEmailLogIn.getText().toString());
                queryUser.findInBackground(new FindCallback<ParseUser>() {
                    @Override
                    public void done(List<ParseUser> objects, ParseException e) {

                        if (e == null) {

                            if (objects.size() > 0) {

                                String username = objects.get(0).get("username").toString();

                                ParseUser.logInInBackground(username, edtPasswordLogIn.getText().toString(), new LogInCallback() {
                                    @Override
                                    public void done(ParseUser user, ParseException e) {

                                        if (user != null && e == null) {

                                            //Toast.makeText(LogIn.this, "Log in successfully!", Toast.LENGTH_SHORT).show();
                                            transitionToSocialMediaActivity();

                                        } else {
                                            Toast.makeText(LogIn.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            } else {
                                Toast.makeText(LogIn.this, "404 user not found!", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(LogIn.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

    private void transitionToSocialMediaActivity() {

        Intent intent = new Intent(LogIn.this, SocialMediaActivity.class);
        startActivity(intent);

    }
}
