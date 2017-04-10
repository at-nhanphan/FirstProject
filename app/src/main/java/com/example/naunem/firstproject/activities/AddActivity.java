package com.example.naunem.firstproject.activities;

import android.support.v7.app.AppCompatActivity;

import com.example.naunem.firstproject.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

/**
 * AddActivity class
 * Created by naunem on 21/03/2017.
 */

@EActivity(R.layout.activity_add_layout)
public class AddActivity extends AppCompatActivity {

    @Click(R.id.btnAdd)
    void onClick() {
        LayoutAddEditActivity_.intent(this).mValue("add").start();
    }
}
