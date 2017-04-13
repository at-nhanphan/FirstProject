package com.example.naunem.firstproject.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.fragments.MyDialogFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * ToolBarActivity class
 * Created by naunem on 30/03/2017.
 */

@EActivity(R.layout.activity_toolbar)
@OptionsMenu(R.menu.menu)
public class ToolBarActivity extends AppCompatActivity {

    @ViewById(R.id.toolBar)
    Toolbar mToolbar;

    @Click(R.id.fab)
    void onClickFloatingButton(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @AfterViews
    void init() {
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_dehaze_white_36dp);
        getSupportActionBar().setTitle(R.string.toolBar_text_name);
        mToolbar.setTitleTextColor(Color.WHITE);
    }

    @OptionsItem({R.id.setting, R.id.dialog, R.id.fragmentDialog, R.id.dialogMultiChoice})
    void onItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:
                createSingleChoiceDialog().show();
                break;
            case R.id.dialog:
                createDialog().show();
                break;
            case R.id.fragmentDialog:
                MyDialogFragment dialogFragment = new MyDialogFragment();
                dialogFragment.createDialogFragment(this, "Fragment Dialog", "Demo Fragment Dialog").show();
                break;
            case R.id.dialogMultiChoice:
                createMultiChoiceDialog().show();
                break;
        }
    }

    public Dialog createDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Dialog")
                .setMessage("Demo Alert Dialog")
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

    public Dialog createSingleChoiceDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        ArrayList<String> items = new ArrayList<>();
        items.add("Red");
        items.add("Blue");
        items.add("Green");

        final ArrayAdapter<String> arrayAdapterItems = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, items);

        dialog.setTitle("Dialog Single Choice Item")
                .setIcon(R.drawable.ic_home_pink_400_24dp)
                .setSingleChoiceItems(arrayAdapterItems, -1, new DialogInterface.OnClickListener() {
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

    public Dialog createMultiChoiceDialog() {
        final ArrayList<Integer> selectedItems = new ArrayList<>();
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("MultiChoice")
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
