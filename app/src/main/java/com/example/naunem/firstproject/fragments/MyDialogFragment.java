package com.example.naunem.firstproject.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import com.example.naunem.firstproject.R;

/**
 *
 * Created by naunem on 30/03/2017.
 */

public class MyDialogFragment extends DialogFragment {

    public AlertDialog createDialogFragment(Context context, String title, String message) {
        return new AlertDialog.Builder(context)
                .setIcon(R.drawable.ic_favorite_pink_400_24dp)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
    }
}
