package com.example.naunem.firstproject.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.naunem.firstproject.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * DemoSharedPreferenceActivity class
 * Created by naunem on 20/03/2017.
 */

@EActivity(R.layout.activity_shared_preference)
public class DemoSharedPreferenceActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    @ViewById(R.id.edtName)
    protected EditText mEdtName;
    @ViewById(R.id.edtAge)
    protected EditText mEdtAge;

    @Click(R.id.btnSave)
    void clickBtnSave() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", String.valueOf(mEdtName.getText()));
        editor.putString("age", String.valueOf(mEdtAge.getText()));
        editor.apply();
    }

    @AfterViews
    void init() {
        preferences = getSharedPreferences("MyPreference", Context.MODE_PRIVATE);
    }
}
