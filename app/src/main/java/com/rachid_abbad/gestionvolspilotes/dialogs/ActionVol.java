package com.rachid_abbad.gestionvolspilotes.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.rachid_abbad.gestionvolspilotes.R;
import com.rachid_abbad.gestionvolspilotes.activities.MainActivity;
import com.rachid_abbad.gestionvolspilotes.activities.VolFragment;
import com.rachid_abbad.gestionvolspilotes.adapters.SpinnerPiloteAdapter;
import com.rachid_abbad.gestionvolspilotes.models.Pilote;
import com.rachid_abbad.gestionvolspilotes.models.Vol;

import java.util.List;

public class ActionVol extends BottomSheetDialogFragment {

    public static final int EDIT = 1;
    public static final int ADD = 2;

    int action;
    public Button btn_action;
    public TextInputEditText numVol, societe;
    public DatePicker dateVol;
    public Spinner numPilote;
    public Vol v ;
    VolFragment volFragment;


    public ActionVol(int action, VolFragment volFragment){
        this.action = action;
        this.volFragment = volFragment;
    }

    public ActionVol(int action, Vol v,VolFragment volFragment){
        this.action = action;
        this.v = v;
        this.volFragment = volFragment;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(@NonNull Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getActivity(), R.layout.action_vol_layout, null);

        dialog.setContentView(contentView);
        ((View) contentView.getParent()).setBackgroundColor(Color.TRANSPARENT);
        dialog.setContentView(contentView);

        numPilote = contentView.findViewById(R.id.pilotes);
        numVol = contentView.findViewById(R.id.num_vol);
        societe = contentView.findViewById(R.id.societe);
        dateVol = contentView.findViewById(R.id.datePicker);
        btn_action = contentView.findViewById(R.id.btn_action);

        //Fill spinner
        List<Pilote> pilotes = MainActivity.databaseHelper.getAllPilotes();

        SpinnerPiloteAdapter adp = new SpinnerPiloteAdapter(getActivity(), android.R.layout.simple_spinner_item,pilotes);
        numPilote.setAdapter(adp);

        if (action == EDIT){
            numVol.setEnabled(false);
            btn_action.setText("Modifier");
        }
        else if(action == ADD)
            btn_action.setText("Ajouter");

        if(v != null){
            numVol.setText(v.getNumVol());
            societe.setText(v.getSociete());
        }

        btn_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (societe.getText().toString().equals("") || numVol.getText().toString().equals(""))
                    Toast.makeText(getActivity(),"Veillez-remplir tout les champs !!", Toast.LENGTH_LONG).show();
                else
                    handelClick();
            }
        });


    }

    void handelClick(){
        if (action == EDIT)
            MainActivity.databaseHelper.editVol(new Vol(numVol.getText().toString(), societe.getText().toString(), dateVol.getDayOfMonth()+"-"+dateVol.getMonth()+"-"+dateVol.getYear(),((Pilote)numPilote.getSelectedItem()).getMatricule()));
        else if(action == ADD){
            MainActivity.databaseHelper.addVol(new Vol(numVol.getText().toString(), societe.getText().toString(), dateVol.getDayOfMonth()+"-"+dateVol.getMonth()+"-"+dateVol.getYear(),((Pilote)numPilote.getSelectedItem()).getMatricule()));
        }
        volFragment.refresh_reset();
        dismiss();
    }
}
