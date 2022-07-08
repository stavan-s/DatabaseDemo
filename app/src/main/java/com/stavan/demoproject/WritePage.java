package com.stavan.demoproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WritePage extends AppCompatActivity {

    EditText nameInput, emailInput, numberInput;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_page);

        nameInput = findViewById(R.id.user_name_input);
        emailInput = findViewById(R.id.user_email_input);
        numberInput = findViewById(R.id.user_number_input);
        submitBtn = findViewById(R.id.submit_btn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = nameInput.getText().toString();
                String email = emailInput.getText().toString();
                String number = numberInput.getText().toString();

                User user = new User(name, email, number);

                DatabaseReference db = FirebaseDatabase.getInstance().getReference();
                db.child("users").child(number).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(WritePage.this, name + " successfully added to the database", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}