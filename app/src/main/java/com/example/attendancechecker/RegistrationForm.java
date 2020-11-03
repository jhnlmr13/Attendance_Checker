package com.example.attendancechecker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationForm extends AppCompatActivity {

    EditText src,full,Email,Pass,ConPAss;
    RadioGroup genders;
    RadioButton rad_Male,rad_Female;
    Button sign_Up;
    ProgressBar pro;
    ImageView image;
    TextView txt_1;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration_form);

        src = findViewById(R.id.edt_SrCode);
        full = findViewById(R.id.edt_fn);
        Email = findViewById(R.id.edt_email);
        Pass = findViewById(R.id.edt_pass);
        ConPAss = findViewById(R.id.edt_Confirmpass);
        genders = findViewById(R.id.radioGender);
        rad_Female = findViewById(R.id.radio_female);
        rad_Male = findViewById(R.id.radio_male);
        sign_Up = findViewById(R.id.buttonRegister);
        pro = findViewById(R.id.progressBar);

        sign_Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("users");


            String SrCode = src.getText().toString().trim();
            String FullNames = full.getText().toString().trim();
            String Emails = Email.getText().toString().trim();
            String Password = Pass.getText().toString().trim();
            String Confirm = ConPAss.getText().toString().trim();
            String Gender = null;

          if(rad_Female.isChecked())
           {
               Gender = rad_Female.getText().toString().trim();
           }
           if(rad_Male.isChecked())
           {
               Gender = rad_Male.getText().toString().trim();
           }
                if (TextUtils.isEmpty(SrCode)) {
                    src.setError("Please enter your SR-Code");
                    src.requestFocus();
                }
               else if (TextUtils.isEmpty(FullNames)) {
                    full.setError("Please enter your Full Name");
                    full.requestFocus();
                }
               else if (TextUtils.isEmpty(Emails)) {
                    Email.setError("Please enter your Email");
                    Email.requestFocus();
                }
              else  if (TextUtils.isEmpty(Password)) {
                    Pass.setError("Please enter your Password");
                    Pass.requestFocus();
                }
              else  if (TextUtils.isEmpty(Confirm)) {
                    ConPAss.setError("Please enter your Confirm Password");
                    ConPAss.requestFocus();
                }
              else  if (Password.length() < 6 && Confirm.length() < 6) {
                    Pass.setError("Your Password is too short");
                    Pass.requestFocus();
                }
               else if(!Password.equals(Confirm))
                {
                    ConPAss.setError("Confirm Password not match");
                    ConPAss.requestFocus();
                }
                else if (!rad_Male.isChecked() && !rad_Female.isChecked()) {
                    Toast.makeText(RegistrationForm.this, "Please select your Gender", Toast.LENGTH_SHORT).show();
                    genders.requestFocus();
                }
                else{
                    UserInfoForm userInfo = new UserInfoForm(SrCode,FullNames,Emails,Password,Gender);
                    reference.child(Emails).setValue(userInfo);
                    Toast.makeText(RegistrationForm.this, "Registration Complete...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationForm.this, LoginForm.class);
                    startActivity(intent);
                }

            }
        });
    }
}