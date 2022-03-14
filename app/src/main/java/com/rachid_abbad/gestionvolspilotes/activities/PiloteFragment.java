package com.rachid_abbad.gestionvolspilotes.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.rachid_abbad.gestionvolspilotes.R;
import com.rachid_abbad.gestionvolspilotes.adapters.PiloteAdapter;
import com.rachid_abbad.gestionvolspilotes.datas.ManageDatabase;
import com.rachid_abbad.gestionvolspilotes.dialogs.ActionPilote;
import com.rachid_abbad.gestionvolspilotes.models.Pilote;

import java.util.List;

public class PiloteFragment extends Fragment {

    RecyclerView rv;
    ImageButton btn_add_pilote;
    List<Pilote> list;
    PiloteAdapter adapter;
    ManageDatabase databaseHelper;
    EditText searchPilote;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_pilote, container, false);

        rv = v.findViewById(R.id.pilotes_rv);
        btn_add_pilote = v.findViewById(R.id.btn_add_pilote);
        searchPilote = v.findViewById(R.id.search_pilote);

        databaseHelper = MainActivity.databaseHelper;

        list = databaseHelper.getAllPilotes();

        adapter = new PiloteAdapter(this,R.layout.pilote_item_layout,list);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        btn_add_pilote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActionPilote ac = new ActionPilote(ActionPilote.ADD,PiloteFragment.this);
                ac.show(getActivity().getSupportFragmentManager(), null);

            }
        });

        searchPilote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable == null || editable.toString() == "" || editable.toString() == null)
                    refresh_reset();
                else{
                    list.clear();
                    list.addAll(databaseHelper.searchPilote(editable.toString()));
                    adapter.notifyDataSetChanged();
                }

            }
        });

        return v;
    }



    public void refresh_reset() {
        list.clear();
        list.addAll(databaseHelper.getAllPilotes());
        adapter.notifyDataSetChanged();
    }
}