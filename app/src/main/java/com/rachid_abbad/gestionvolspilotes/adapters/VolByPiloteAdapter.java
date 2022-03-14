package com.rachid_abbad.gestionvolspilotes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rachid_abbad.gestionvolspilotes.R;
import com.rachid_abbad.gestionvolspilotes.activities.VolFragment;
import com.rachid_abbad.gestionvolspilotes.activities.VolsByPiloteFragment;
import com.rachid_abbad.gestionvolspilotes.dialogs.ActionPilote;
import com.rachid_abbad.gestionvolspilotes.dialogs.ActionVol;
import com.rachid_abbad.gestionvolspilotes.dialogs.DeleteDialogs;
import com.rachid_abbad.gestionvolspilotes.models.Vol;

import java.util.List;

public class VolByPiloteAdapter extends RecyclerView.Adapter<VolByPiloteAdapter.Holder>{
    VolsByPiloteFragment context;
    int res;
    List<Vol> listVols;

    public VolByPiloteAdapter(VolsByPiloteFragment context, int res, List<Vol> listVols){
        this.context = context;
        this.res = res;
        this.listVols = listVols;
    }

    @NonNull
    @Override
    public VolByPiloteAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context.getActivity()).inflate(res,parent,false);
        return new VolByPiloteAdapter.Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VolByPiloteAdapter.Holder holder, int position) {

        holder.matricule.setText(listVols.get(position).getNumMatricule());
        holder.date.setText(listVols.get(position).getDate());
        holder.volNum.setText(listVols.get(position).getNumVol());
        holder.societe.setText(listVols.get(position).getSociete());

    }

    @Override
    public int getItemCount() {
        return listVols.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        CardView container;
        TextView matricule, societe, date, volNum;
        public Holder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.containerPilote);
            matricule = itemView.findViewById(R.id.pilote);
            societe = itemView.findViewById(R.id.societe);
            volNum = itemView.findViewById(R.id.vol_num);
            date = itemView.findViewById(R.id.date);
        }
    }
}
