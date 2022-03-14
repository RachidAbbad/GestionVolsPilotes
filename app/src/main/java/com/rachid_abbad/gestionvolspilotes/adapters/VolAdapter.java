package com.rachid_abbad.gestionvolspilotes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rachid_abbad.gestionvolspilotes.R;
import com.rachid_abbad.gestionvolspilotes.activities.VolFragment;
import com.rachid_abbad.gestionvolspilotes.dialogs.ActionPilote;
import com.rachid_abbad.gestionvolspilotes.dialogs.ActionVol;
import com.rachid_abbad.gestionvolspilotes.dialogs.DeleteDialogs;
import com.rachid_abbad.gestionvolspilotes.models.Pilote;
import com.rachid_abbad.gestionvolspilotes.models.Vol;

import java.util.List;

public class VolAdapter extends RecyclerView.Adapter<VolAdapter.Holder>{
    VolFragment context;
    int res;
    List<Vol> listVols;

    public VolAdapter(VolFragment context, int res, List<Vol> listVols){
        this.context = context;
        this.res = res;
        this.listVols = listVols;
    }

    @NonNull
    @Override
    public VolAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context.getActivity()).inflate(res,parent,false);
        return new VolAdapter.Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VolAdapter.Holder holder, int position) {

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ActionVol(ActionPilote.EDIT,listVols.get(position),context).show(context.getActivity().getSupportFragmentManager(),null);
            }
        });

        holder.container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new DeleteDialogs("", listVols.get(position).getNumVol(), context).show(context.getActivity().getSupportFragmentManager(),null);
                return true;
            }
        });

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
