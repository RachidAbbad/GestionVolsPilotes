package com.rachid_abbad.gestionvolspilotes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.rachid_abbad.gestionvolspilotes.R;
import com.rachid_abbad.gestionvolspilotes.models.Pilote;

import java.util.List;

public class SpinnerPiloteAdapter extends BaseAdapter {

    Context context;
    int res;
    List<Pilote> listPilotes;

    public SpinnerPiloteAdapter(Context context, int res, List<Pilote> listPilotes){
        this.context = context;
        this.res = res;
        this.listPilotes = listPilotes;
    }

    @Override
    public int getCount() {
        return listPilotes.size();
    }

    @Override
    public Object getItem(int i) {
        return listPilotes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.pilote_item_layout, null);
        TextView name = view.findViewById(R.id.name);
        TextView matricule = view.findViewById(R.id.matricule);
        name.setText(listPilotes.get(i).getName());
        matricule.setText(listPilotes.get(i).getMatricule());
        return view;
    }
}
