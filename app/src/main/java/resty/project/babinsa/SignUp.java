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

import resty.project.babinsa.Model.User;

public class SignUp extends AppCompatActivity {
    EditText editNRP, editName, editPass, editJabatan;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editName = (EditText) findViewById(R.id.etName);
        editNRP = (EditText) findViewById(R.id.etNRP);
        editJabatan = (EditText) findViewById(R.id.etJabatan);
        editPass = (EditText) findViewById(R.id.etPassword);
        btnSignUp = (Button) findViewById(R.id.btnRegister);

        if (editPass.length() < 6) {
            editPass.setError("Password Minimal 6 Karakter");

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference table_user = database.getReference("User");

            btnSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                    mDialog.setMessage("Please wait...");
                    mDialog.show();

                    table_user.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if (dataSnapshot.child(editNRP.getText().toString()).exists()) {
                                mDialog.dismiss();
                                Toast.makeText(SignUp.this, "NRP Sudah Terdaftar", Toast.LENGTH_SHORT).show();

                            } else {
                                mDialog.dismiss();
                                User user = new User(editName.getText().toString(), editNRP.getText().toString(),
                                        editJabatan.getText().toString(), editPass.getText().toString());
                                table_user.child(editNRP.getText().toString()).setValue(user);
                                Toast.makeText(SignUp.this, "Register Success", Toast.LENGTH_SHORT).show();
                                finish();
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