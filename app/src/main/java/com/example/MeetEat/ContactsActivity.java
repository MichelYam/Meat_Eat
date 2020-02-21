package com.example.MeetEat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;


public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        TextView c_instagram = findViewById(R.id.insta);
        TextView c_facebook = findViewById(R.id.facebook);
        TextView c_mail = findViewById(R.id.email);

        c_facebook.setMovementMethod(LinkMovementMethod.getInstance());
        c_instagram.setMovementMethod(LinkMovementMethod.getInstance());

        c_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mailIntent = new Intent (ContactsActivity.this, SendMailActivity.class);
                startActivity(mailIntent);
            }
        });
    }
}
