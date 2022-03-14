package com.rachid_abbad.gestionvolspilotes.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.rachid_abbad.gestionvolspilotes.R;
import com.rachid_abbad.gestionvolspilotes.adapters.SpinnerPiloteAdapter;
import com.rachid_abbad.gestionvolspilotes.adapters.VolAdapter;
import com.rachid_abbad.gestionvolspilotes.adapters.VolByPiloteAdapter;
import com.rachid_abbad.gestionvolspilotes.datas.ManageDatabase;
import com.rachid_abbad.gestionvolspilotes.models.Pilote;
import com.rachid_abbad.gestionvolspilotes.models.Vol;

import java.util.ArrayList;
import java.util.List;

public class VolsByPiloteFragment extends Fragment {

    RecyclerView rv;
    List<Vol> list;
    VolByPiloteAdapter adapter;
    ManageDatabase databaseHelper;
    Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_vols_by_pilote, container, false);
        rv = v.findViewById(R.id.vol_by_pilote_rv);
        spinner = v.findViewById(R.id.matricule_spinner);

        databaseHelper = MainActivity.databaseHelper;

        list = new ArrayList<Vol>();

        adapter = new VolByPiloteAdapter(this,R.layout.vol_item_layout,list);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        refresh_reset();

        List<Pilote> pilotes = MainActivity.databaseHelper.getAllPilotes();

        SpinnerPiloteAdapter adp = new SpinnerPiloteAdapter(getActivity(), android.R.layout.simple_spinner_item,pilotes);
        spinner.setAdapter(adp);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                list.clear();
                list.addAll(databaseHelper.searchVol(((Pilote)spinner.getSelectedItem()).getMatricule()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                refresh_reset();
            }
        });
        return v;
    }

    public void refresh_reset() {
        list.clear();
        list.addAll(databaseHelper.getAllVols());
        adapter.notifyDataSetChanged();
    }
}