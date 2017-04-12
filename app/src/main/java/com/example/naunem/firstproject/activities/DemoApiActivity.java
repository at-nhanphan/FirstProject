package com.example.naunem.firstproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.naunem.firstproject.ApiUtils;
import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.adapters.AnswerAdapter;
import com.example.naunem.firstproject.interfaces.SOService;
import com.example.naunem.firstproject.models.Item;
import com.example.naunem.firstproject.models.SOAnswersResponse;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class use to demo api
 * Created by naunem on 05/04/2017.
 */

@EActivity(R.layout.activity_list_api_answer)
public class DemoApiActivity extends AppCompatActivity {

    private SOService mSoService;
    private AnswerAdapter mAdapter;

    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @AfterViews
    void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mSoService = ApiUtils.getSOService();
        ArrayList<Item> items = new ArrayList<>();
        mAdapter = new AnswerAdapter(this, items);
        mRecyclerView.setAdapter(mAdapter);

        loadAnswers();
    }

    public void loadAnswers() {
        mSoService.getAnswers("android").enqueue(new Callback<SOAnswersResponse>() {
            @Override
            public void onResponse(Call<SOAnswersResponse> call, Response<SOAnswersResponse> response) {
                if (response.isSuccessful()) {
                    mAdapter.updateAnswers(response.body().getItems());
                } else {
                    Log.d("Error", "onResponse: ");
                }
            }

            @Override
            public void onFailure(Call<SOAnswersResponse> call, Throwable t) {
                Log.d("Error", "onFailure: ");
            }
        });
    }
}
