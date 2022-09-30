package com.practicadappm.bancoutn;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class DialogoExito extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        return new AlertDialog.Builder(getActivity())
                .setTitle(bundle.getString("titulo"))
                .setMessage(bundle.getString("mensaje"))
                .setCancelable(false)
                .setPositiveButton("Joya", (DialogInterface.OnClickListener) (dialog, which) -> {
                    // Cuando el usuario hace click en piola la app termina
                    getActivity().finish();
                })
                .create();
    }
}
