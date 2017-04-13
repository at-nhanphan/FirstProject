package com.example.naunem.firstproject.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.models.User;
import com.example.naunem.firstproject.models.UserDatabase;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * SQLiteActivity class
 * Created by naunem on 17/03/2017.
 */

@EActivity(R.layout.activity_sqlite)
public class SQLiteActivity extends AppCompatActivity {

    private final UserDatabase db = new UserDatabase(this);

    @ViewById(R.id.edtName)
    EditText mEdtName;
    @ViewById(R.id.edtAge)
    EditText mEdtAge;
    @ViewById(R.id.edtGender)
    EditText mEdtGender;

    @Click(R.id.btnInsert)
    void clickInsert() {
        User user = new User();
        db.insertUser(user);
        mEdtGender.setText("size " + db.getAllUsers().size());
    }

    @Click(R.id.btnShowInfo)
    void clickShowInfo() {
        Intent intent = new Intent(this, SQLiteShowListActivity.class);
        startActivity(intent);
    }
}
