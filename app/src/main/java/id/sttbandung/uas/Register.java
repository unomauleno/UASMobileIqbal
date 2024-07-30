package id.sttbandung.uas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private EditText Email, Username, Password;
    private Button BtnRegister;

    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Email = findViewById(R.id.Email);
        Username = findViewById(R.id.Username);
        Password = findViewById(R.id.Password);
        BtnRegister = findViewById(R.id.BtnRegister);

        database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://uasmobile-6ccbb-default-rtdb.firebaseio.com/");

        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = Email.getText().toString();
                String username = Username.getText().toString();
                String password = Password.getText().toString();

                if(username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Ada data yang masih kosong", Toast.LENGTH_SHORT).show();
                }else{
                    database = FirebaseDatabase.getInstance().getReference("users");
                    database.child(username).child("email").setValue(email);
                    database.child(username).child("username").setValue(username);
                    database.child(username).child("password").setValue(password);

                    Toast.makeText(getApplicationContext(), "Register Berhasil", Toast.LENGTH_SHORT).show();
                    Intent register = new Intent(getApplicationContext(), Login.class);
                    startActivity(register);
                }

            }
        });

    }
}