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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.rachid_abbad.gestionvolspilotes.R;
import com.rachid_abbad.gestionvolspilotes.adapters.PiloteAdapter;
import com.rachid_abbad.gestionvolspilotes.adapters.VolAdapter;
import com.rachid_abbad.gestionvolspilotes.datas.ManageDatabase;
import com.rachid_abbad.gestionvolspilotes.dialogs.ActionPilote;
import com.rachid_abbad.gestionvolspilotes.dialogs.ActionVol;
import com.rachid_abbad.gestionvolspilotes.models.Vol;

import java.util.List;

public class VolFragment extends Fragment {

    RecyclerView rv;
    ImageButton btn_add_pilote;
    List<Vol> list;
    VolAdapter adapter;
    ManageDatabase databaseHelper;
    EditText search_vol;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_vol, container, false);

        rv = v.findViewById(R.id.vol_rv);
        btn_add_pilote = v.findViewById(R.id.btn_add_vol);
        search_vol = v.findViewById(R.id.search_vol);

        databaseHelper = MainActivity.databaseHelper;

        list = databaseHelper.getAllVols();

        adapter = new VolAdapter(this,R.layout.vol_item_layout,list);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        btn_add_pilote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActionVol ac = new ActionVol(ActionVol.ADD,VolFragment.this);
                ac.show(getActivity().getSupportFragmentManager(), null);
            }
        });

        search_vol.addTextChangedListener(new TextWatcher() {
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
                    list.addAll(MainActivity.databaseHelper.searchVol(editable.toString()));
                    adapter.notifyDataSetChanged();
                }

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