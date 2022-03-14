package com.rachid_abbad.gestionvolspilotes.adapters;

import android.app.Activity;
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
import com.rachid_abbad.gestionvolspilotes.activities.MainActivity;
import com.rachid_abbad.gestionvolspilotes.activities.PiloteFragment;
import com.rachid_abbad.gestionvolspilotes.dialogs.ActionPilote;
import com.rachid_abbad.gestionvolspilotes.dialogs.DeleteDialogs;
import com.rachid_abbad.gestionvolspilotes.models.Pilote;

import java.util.List;

public class PiloteAdapter extends RecyclerView.Adapter<PiloteAdapter.Holder> {

    PiloteFragment context;
    int res;
    List<Pilote> listPilotes;

    public PiloteAdapter(PiloteFragment context, int res, List<Pilote> listPilotes){
        this.context = context;
        this.res = res;
        this.listPilotes = listPilotes;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context.getActivity()).inflate(res,parent,false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ActionPilote(ActionPilote.EDIT,listPilotes.get(position), context).show(context.getActivity().getSupportFragmentManager(),null);
            }
        });

        holder.container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new DeleteDialogs("", listPilotes.get(position).getMatricule(), context).show(context.getActivity().getSupportFragmentManager(),null);
                return true;
            }
        });

        holder.matricule.setText(listPilotes.get(position).getMatricule());
        holder.name.setText(listPilotes.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return listPilotes.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        CardView container;
        TextView matricule, name;
        public Holder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.containerPilote);
            matricule = itemView.findViewById(R.id.matricule);
            name = itemView.findViewById(R.id.name);
        }
    }
}
