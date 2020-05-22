package neversmile.packag.com.instagramcloneapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Welcome extends AppCompatActivity {

    private TextView txtWelcome;
    private Button btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        txtWelcome = findViewById(R.id.txtWelcome);
        txtWelcome.setText(ParseUser.getCurrentUser().get("username").toString());

        btnLogOut = findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logOutInBackground(new LogOutCallback() {
                    @Override
                    public void done(ParseException e) {

                        if (e == null) {

                            Toast.makeText(Welcome.this, "Log out successfully!", Toast.LENGTH_SHORT).show();
                            finish();

                        } else {

                            Toast.makeText(Welcome.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });

            }
        });

    }
}
