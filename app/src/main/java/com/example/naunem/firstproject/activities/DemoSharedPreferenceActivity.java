package com.example.naunem.firstproject.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.naunem.firstproject.R;

/**
 * Created by naunem on 20/03/2017.
 */

public class DemoSharedPreferenceActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtName;
    private EditText mEdtAge;
    private Button mBtnSave;
    private SharedPreferences preferences;

    private void init() {
        mEdtName = (EditText) findViewById(R.id.edtName);
        mEdtAge = (EditText) findViewById(R.id.edtAge);
        mBtnSave = (Button) findViewById(R.id.btnSave);
        mBtnSave.setOnClickListener(this);
        preferences = getSharedPreferences("MyPreference", Context.MODE_PRIVATE);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_preference_activity);
        init();
    }

    @Override
    public void onClick(View v) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", String.valueOf(mEdtName.getText()));
        editor.putString("age", String.valueOf(mEdtAge.getText()));
        editor.commit();
    }
}
