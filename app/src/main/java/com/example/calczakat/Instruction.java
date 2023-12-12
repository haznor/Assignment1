package com.example.calczakat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;

public class Instruction extends AppCompatActivity {
    Toolbar my_toolbarins;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        my_toolbarins =(androidx.appcompat.widget.Toolbar) findViewById(R.id.my_toolbar_ins);

            setSupportActionBar(my_toolbarins);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
           getSupportActionBar().setTitle("Instruction");
        }
        public boolean onOptionsItemSelected(MenuItem item){
            if (item.getItemId() == android.R.id.home){
                super.onBackPressed();
            }
            return true;
        }
}