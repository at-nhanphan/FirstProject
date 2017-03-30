package com.example.naunem.firstproject.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.fragments.MyDialogFragment;

import java.util.ArrayList;

/**
 *
 * Created by naunem on 30/03/2017.
 */

public class ToolBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        Toolbar mToolBar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(mToolBar);
        mToolBar.setNavigationIcon(R.drawable.ic_dehaze_white_36dp);
        getSupportActionBar().setTitle(R.string.toolBar_text_name);
        mToolBar.setTitleTextColor(Color.WHITE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:
                createSingleChoiceDialog("Dialog Single Choice Item").show();
                break;
            case R.id.dialog:
                createDialog("Dialog", "Demo Alert Dialog").show();
                break;
            case R.id.fragmentDialog:
                MyDialogFragment dialogFragment = new MyDialogFragment();
                dialogFragment.createDialogFragment(this, "Fragment Dialog", "Demo Fragment Dialog").show();
                break;
            case R.id.dialogMultiChoice:
                createMultiChoiceDialog("MutiChoice").show();
                break;
        }
        return true;
    }

    public Dialog createDialog(String title, String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(title)
                .setMessage(message)
                .setIcon(R.drawable.ic_home_pink_400_24dp)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return dialog.create();
    }

    public Dialog createSingleChoiceDialog(String title) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        ArrayList<String> items = new ArrayList<>();
        items.add("Red");
        items.add("Blue");
        items.add("Green");

        final ArrayAdapter<String> arrayAdapterItems = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, items);

        dialog.setTitle(title)
                .setIcon(R.drawable.ic_home_pink_400_24dp)
                .setSingleChoiceItems(arrayAdapterItems, -1,  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return dialog.create();
    }

    public Dialog createMultiChoiceDialog(String title) {
        final ArrayList<Integer> selectedItems = new ArrayList<>();
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(title)
                .setMultiChoiceItems(R.array.popping, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            // If the user checked the item, add it to the selected items
                            selectedItems.add(which);
                        } else if (selectedItems.contains(which)) {
                            // Else, if the item is already in the array, remove it
                            selectedItems.remove(Integer.valueOf(which));
                        }
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return dialog.create();
    }
}
