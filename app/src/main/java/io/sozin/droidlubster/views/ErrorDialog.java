package io.sozin.droidlubster.views;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import ve.com.anake.libs.lubster.R;

/**
 * Created by tes1oner on 06/12/17.
 */

public class ErrorDialog extends DialogFragment{
    String message, title, btnText;
    public ErrorDialog(){

    }
    public void setArgs(String title, String message, String btnText){
        this.message = message;
        this.title = title;
        this.btnText = btnText;
    }
    private void setArgs(String title, String message){
        this.message = message;
        this.title = title;
        this.btnText = getString(R.string.action_accept);
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());

        builder.setMessage(message)
                .setTitle(title)
                .setPositiveButton(btnText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        return builder.create();
    }
}