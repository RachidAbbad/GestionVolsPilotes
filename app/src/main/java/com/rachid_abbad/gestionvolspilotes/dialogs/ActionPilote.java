package com.rachid_abbad.gestionvolspilotes.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.rachid_abbad.gestionvolspilotes.R;
import com.rachid_abbad.gestionvolspilotes.activities.MainActivity;
import com.rachid_abbad.gestionvolspilotes.activities.PiloteFragment;
import com.rachid_abbad.gestionvolspilotes.models.Pilote;

public class ActionPilote extends BottomSheetDialogFragment {
    public static final int EDIT = 1;
    public static final int ADD = 2;
    public Button btn_action;
    public TextInputEditText matricule_zone, name_zone;
    public Pilote p;
    int action;
    PiloteFragment piloteFragment;

    public ActionPilote(int action, PiloteFragment piloteFragment) {
        this.action = action;
        this.piloteFragment = piloteFragment;
    }

    public ActionPilote(int action, Pilote p , PiloteFragment piloteFragment) {
        this.action = action;
        this.p = p;
        this.piloteFragment = piloteFragment;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(@NonNull Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getActivity(), R.layout.action_pilote_layout, null);
        dialog.setContentView(contentView);

        matricule_zone = contentView.findViewById(R.id.matricule_zone);
        name_zone = contentView.findViewById(R.id.nom_pilote_zone);
        btn_action = contentView.findViewById(R.id.btn_action);

        if (action == EDIT)
            btn_action.setText("Modifier");
        else if (action == ADD)
            btn_action.setText("Ajouter");

        if (p != null) {
            name_zone.setText(p.getName());
            matricule_zone.setText(p.getMatricule());
        }

        btn_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (matricule_zone.getText().toString() == null || name_zone.getText().toString() == null || matricule_zone.getText().toString().equals("") || name_zone.getText().toString().equals(""))
                    Toast.makeText(getActivity(),"Veillez-remplir tout les champs !!", Toast.LENGTH_LONG).show();
                else
                    handelClick();
            }
        });


    }

    void handelClick() {
        if (action == EDIT) {
            MainActivity.databaseHelper.editPilote(new Pilote(matricule_zone.getText().toString(), name_zone.getText().toString()));
        } else if (action == ADD) {
            MainActivity.databaseHelper.addPilote(new Pilote(matricule_zone.getText().toString(), name_zone.getText().toString()));
        }
        piloteFragment.refresh_reset();
        dismiss();
    }


}
