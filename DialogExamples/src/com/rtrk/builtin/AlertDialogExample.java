package com.rtrk.builtin;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class AlertDialogExample extends Dialog {
	Context context;
	public Dialog dialog;
	public AlertDialogExample(Activity act) {
		super((Context)act);
		this.context = (Context)act;
		AlertDialog.Builder builder = new AlertDialog.Builder(act);
		builder.setMessage(
				"Are you sure you want to DELETE city Novi Sad?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int id) {
								Toast.makeText(
										AlertDialogExample.this.context,
										"DELETING city Novi Sad..."
										, Toast.LENGTH_LONG)
											.show();
							}
						})
				.setNegativeButton("No",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int id) {
								dialog.cancel();
							}
						});
		dialog = builder.create();
	}

}
