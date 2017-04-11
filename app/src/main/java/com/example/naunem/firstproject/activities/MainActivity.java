package com.example.naunem.firstproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.naunem.firstproject.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * This is MainActivity
 * Created by naunem on 15/03/2017.
 */

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.btnTestRecyclerView)
    Button btnTestRecyclerView;

    @Click(R.id.btnTestRecyclerView)
    void clickButton() {
        LoginActivity_.intent(this).start();
    }

    @Click(R.id.btnTestIntent)
    void clickButtonTestIntent() {
        PhoneCallActivity_.intent(this).start();
    }

    @Click(R.id.btnCheckIntent)
    void clickButtonCheckIntent() {
        IntentActivity_.intent(this).start();
    }

    @Click(R.id.btnSqlite)
    void clickBtnSqlite() {
        SQLiteActivity_.intent(this).start();
    }

    @Click(R.id.btnFragment)
    void clickBtnFragment() {

    }

    @Click(R.id.btnViewPager)
    void clickBtnViewPager() {
        ViewPagerActivity_.intent(this).start();
    }

    @Click(R.id.btnMap)
    void clickBtnMap() {
        MapsActivity_.intent(this).start();
    }

    @Click(R.id.btnAsyncTask)
    void clickBtnAsyncTask() {

    }
}
