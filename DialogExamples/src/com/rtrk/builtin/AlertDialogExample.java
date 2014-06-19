package com.rtrk.builtin;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class AlertDialogExample extends Dialog {
    public Dialog mDialog = null;

    public AlertDialogExample(final Activity activity) {
        super((Context) activity);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Are you sure you want to DELETE city Novi Sad?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(activity,
                                        "DELETING city Novi Sad...",
                                        Toast.LENGTH_LONG).show();
                            }
                        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        mDialog = builder.create();
    }

    public Dialog getDialog() {
        return mDialog;
    }
}
