package com.example.naunem.firstproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.naunem.firstproject.R;

/**
 * Created by naunem on 21/03/2017.
 */

public class AddActivity extends AppCompatActivity {
    private Button mBtnAdd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_layout);
        mBtnAdd = (Button) findViewById(R.id.btnAdd);
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, LayoutAddEditActitvity.class);
                intent.putExtra("value", "add");
                startActivity(intent);
            }
        });
    }
}
