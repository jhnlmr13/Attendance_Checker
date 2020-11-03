package com.example.attendancechecker;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ThrowOnExtraProperties;
import com.google.firebase.database.ValueEventListener;

public class LoginForm extends AppCompatActivity {

    EditText txtEmail, txtPassword;
    Button btn_Login, btn_Register;
    ImageView logo;
    TextView txt_1,txt_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_form);

        btn_Register = findViewById(R.id.btnRegister);
        btn_Login = findViewById(R.id.btnLogin);
        txtEmail = findViewById(R.id.txt_email);
        txtPassword = findViewById(R.id.txt_password);
        logo = findViewById(R.id.main_logo);
        txt_1 = findViewById(R.id.textView1);
        txt_2 = findViewById(R.id.textView2);

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String emails = txtEmail.getText().toString().trim();
                final String pass = txtPassword.getText().toString().trim();

               if(TextUtils.isEmpty(emails) && TextUtils.isEmpty(pass)) {
                   Toast.makeText(LoginForm.this, "Please enter your Email and Password", Toast.LENGTH_SHORT).show();
                   txtPassword.requestFocus();
                   txtEmail.requestFocus();
               }
               else if(TextUtils.isEmpty(emails)) {
                   txtEmail.setError("Please enter your Email");
                   txtEmail.requestFocus();
               }
               else if(TextUtils.isEmpty(pass)) {
                   txtPassword.setError("Please enter your Password");
                   txtPassword.requestFocus();
               }
               else {
                   DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
                   Query checkUsers = reference.orderByChild("email").equalTo(emails);
                   checkUsers.addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot snapshot) {
                           if(snapshot.exists()) {
                               String passFDB = snapshot.child(emails).child("password").getValue(String.class);

                               if(passFDB.equals(pass)) {
                                   Toast.makeText(LoginForm.this, "Login Successfully...", Toast.LENGTH_SHORT).show();
                                   Intent intents = new Intent(LoginForm.this,StudentForm.class);
                                   startActivity(intents);

                               }
                               else {
                                   txtPassword.setError("Wrong Password");
                                   txtPassword.requestFocus();
                               }
                           }
                           else {
                               txtEmail.setError("No such Email exist");
                               txtEmail.requestFocus();
                           }
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError error) {

                       }
                   });

               }
            }
        });
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(LoginForm.this,RegistrationForm.class);
                startActivity(intents);
            }
        });

    }


}