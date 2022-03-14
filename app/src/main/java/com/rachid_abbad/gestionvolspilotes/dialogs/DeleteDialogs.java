package com.rachid_abbad.gestionvolspilotes.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.rachid_abbad.gestionvolspilotes.R;
import com.rachid_abbad.gestionvolspilotes.activities.MainActivity;
import com.rachid_abbad.gestionvolspilotes.activities.PiloteFragment;
import com.rachid_abbad.gestionvolspilotes.activities.VolFragment;
import com.rachid_abbad.gestionvolspilotes.models.Pilote;

public class DeleteDialogs extends BottomSheetDialogFragment {

    String msg;
    public static int TYPE_PILOTE = 0,TYPE_VOL = 1;
    int type_delete;
    String id;
    VolFragment volFragment;
    PiloteFragment piloteFragment;

    Button btn_delete, btn_exit;
    TextView msgContainer;

    public DeleteDialogs(String msg, String id, PiloteFragment p) {
        this.msg = msg;
        this.type_delete = TYPE_PILOTE;
        this.id = id;
        this.piloteFragment = p;
    }

    public DeleteDialogs(String msg, String id, VolFragment v) {
        this.msg = msg;
        this.type_delete = TYPE_VOL;
        this.id = id;
        this.volFragment = v;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(@NonNull Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getActivity(), R.layout.delete_dialog_layout, null);

        dialog.setContentView(contentView);
        ((View) contentView.getParent()).setBackgroundColor(Color.TRANSPARENT);
        dialog.setContentView(contentView);

        msgContainer = contentView.findViewById(R.id.mesg_container);
        btn_delete = contentView.findViewById(R.id.btn_delete);
        btn_exit = contentView.findViewById(R.id.btn_exit);

        if(type_delete == TYPE_PILOTE){
            msgContainer.setText(msgContainer.getText()+" pilote ?");
        }else if(type_delete == TYPE_VOL){
            msgContainer.setText(msgContainer.getText()+" vol ?");
        }

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteDialogs.this.dismiss();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type_delete == TYPE_PILOTE){
                    MainActivity.databaseHelper.deletePilote(id);
                    piloteFragment.refresh_reset();
                }else if(type_delete == TYPE_VOL){
                    MainActivity.databaseHelper.deleteVol(id);
                    volFragment.refresh_reset();
                }
                DeleteDialogs.this.dismiss();
            }
        });
    }

}
