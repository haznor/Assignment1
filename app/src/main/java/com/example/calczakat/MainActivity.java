package com.example.calczakat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCalc, btnProfile;
    Toolbar mytoolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mytoolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setTitle(R.string.app_name);


        btnCalc = findViewById(R.id.btnZakatCalc);
        btnProfile = findViewById(R.id.btnProfile);

        btnCalc.setOnClickListener(this);
        btnProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnCalc) {
            Intent intent = new Intent(this, ZakatCalcActivity.class);
            startActivity(intent);
        } else if (view == btnProfile) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        }


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== R.id.item_share){
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent, null));
        }

        return false;

    }

}
