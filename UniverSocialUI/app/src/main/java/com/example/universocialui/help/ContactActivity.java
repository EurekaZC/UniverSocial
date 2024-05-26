package com.example.universocialui.help;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.universocialui.R;
import com.example.universocialui.menu.MenuActivity;

public class ContactActivity extends AppCompatActivity {

    private Button faqButton;
    private Button contactButton;
    private Button menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);

        faqButton = findViewById(R.id.faqButton);
        contactButton = findViewById(R.id.contactButton);
        menuButton = findViewById(R.id.menuButton);

        faqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

