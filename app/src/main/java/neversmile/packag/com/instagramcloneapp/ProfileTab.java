package neversmile.packag.com.instagramcloneapp;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

    private EditText edtProfileName, edtProfileBio, edtProfileProfession, edtProfileHobbies, edtProfileFavoriteSport;
    private Button btnUpdateInfo;

    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);

        edtProfileName = view.findViewById(R.id.edtProfileName);
        edtProfileBio = view.findViewById(R.id.edtProfileBio);
        edtProfileProfession = view.findViewById(R.id.edtProfileProfession);
        edtProfileHobbies = view.findViewById(R.id.edtProfileHobbies);
        edtProfileFavoriteSport = view.findViewById(R.id.edtProfileFavoriteSport);

        final ParseUser parseUser = ParseUser.getCurrentUser();

        edtProfileName.setText((parseUser.get("profileName") == null) ? "" : parseUser.get("profileName").toString());
        edtProfileBio.setText((parseUser.get("profileBio") == null) ? "" : parseUser.get("profileBio").toString());
        edtProfileProfession.setText((parseUser.get("profileProfession") == null) ? "" : parseUser.get("profileProfession").toString());
        edtProfileHobbies.setText((parseUser.get("profileHobbies") == null) ? "" : parseUser.get("profileHobbies").toString());
        edtProfileFavoriteSport.setText((parseUser.get("profileFavoriteSport") == null) ? "" : parseUser.get("profileFavoriteSport").toString());

        btnUpdateInfo = view.findViewById(R.id.btnUpdateInfo);
        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parseUser.put("profileName", edtProfileName.getText().toString());
                parseUser.put("profileBio", edtProfileBio.getText().toString());
                parseUser.put("profileProfession", edtProfileProfession.getText().toString());
                parseUser.put("profileHobbies", edtProfileHobbies.getText().toString());
                parseUser.put("profileFavoriteSport", edtProfileFavoriteSport.getText().toString());

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {

                        ProgressDialog progressDialog = new ProgressDialog(getContext());
                        progressDialog.setMessage("Updating info...");
                        progressDialog.show();

                        if (e == null) {

                            Toast.makeText(getContext(), "Info updated", Toast.LENGTH_SHORT).show();

                        } else {

                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                        progressDialog.dismiss();

                    }
                });

            }
        });

        return view;
    }
}
