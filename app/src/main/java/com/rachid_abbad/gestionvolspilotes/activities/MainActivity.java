package com.rachid_abbad.gestionvolspilotes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.rachid_abbad.gestionvolspilotes.R;
import com.rachid_abbad.gestionvolspilotes.datas.ManageDatabase;

public class MainActivity extends AppCompatActivity {
    Button btn_pilotes, btn_vols, btn_vols_by_pilote;
    FrameLayout fragmentContainer;

    VolFragment volFragment;
    PiloteFragment piloteFragment;
    VolsByPiloteFragment volsByPiloteFragment;

    public static ManageDatabase databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_pilotes = findViewById(R.id.btn_pilotes);
        btn_vols = findViewById(R.id.btn_vols);
        btn_vols_by_pilote = findViewById(R.id.btn_vols_by_pilote);
        fragmentContainer = findViewById(R.id.fragmentContainer);

        databaseHelper = new ManageDatabase(this);

        piloteFragment = new PiloteFragment();
        volFragment = new VolFragment();
        volsByPiloteFragment = new VolsByPiloteFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,piloteFragment).commit();

        btn_pilotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,piloteFragment).commit();
            }
        });

        btn_vols.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,volFragment).commit();
            }
        });

        btn_vols_by_pilote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,volsByPiloteFragment).commit();
            }
        });
    }
}