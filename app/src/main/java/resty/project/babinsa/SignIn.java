package resty.project.babinsa;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import resty.project.babinsa.Model.User;

public class SignIn extends AppCompatActivity {
    EditText editUsername, editPass;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editUsername = (EditText) findViewById(R.id.editUsername);
        editPass = (EditText) findViewById(R.id.editPass);
        btnSignIn = (Button) findViewById(R.id.btnLogin);

        if (editPass.length() < 6) {
            editPass.setError("Password Minimal 6 Karakter");

            //login firebase
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference table_user = database.getReference("User");

            btnSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                    mDialog.setMessage("Please waiting...");
                    mDialog.show();

                    table_user.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            //check if user not exist in database
                            if (dataSnapshot.child(editUsername.getText().toString()).exists()) {
                                //get user information
                                mDialog.dismiss();
                                User user = dataSnapshot.child(editUsername.getText().toString()).getValue(User.class);
                                if (user.getPassword().equals(editPass.getText().toString())) {
                                    Toast.makeText(SignIn.this, "Sign In Successfully", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(SignIn.this, "Password salah", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                mDialog.dismiss();
                                Toast.makeText(SignIn.this, "Username tidak tersedia, silahkan daftar dulu!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            });
        }
    }
}
