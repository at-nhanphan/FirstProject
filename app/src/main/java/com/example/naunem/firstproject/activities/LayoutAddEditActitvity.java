package com.example.naunem.firstproject.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.models.User;
import com.example.naunem.firstproject.models.UserDatabase;
import com.squareup.picasso.Picasso;

/**
 * Created by naunem on 21/03/2017.
 */

public class LayoutAddEditActitvity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTvTitle;
    private ImageView mImgAvatar;
    private EditText mEdtName;
    private EditText mEdtAge;
    private EditText mEdtGender;
    private Button mBtnAddEdit;
    private Button mBtnRemove;
    private String mValue;
    private User mUser;
    private UserDatabase mUserDatabase = new UserDatabase(this);
    private String path;

    private void init() {
        mTvTitle = (TextView) findViewById(R.id.tvTitle);
        mImgAvatar = (ImageView) findViewById(R.id.imgAvatar);
        mEdtName = (EditText) findViewById(R.id.edtName);
        mEdtAge = (EditText) findViewById(R.id.edtAge);
        mEdtGender = (EditText) findViewById(R.id.edtGender);
        mBtnAddEdit = (Button) findViewById(R.id.btnAdd);
        mBtnRemove = (Button) findViewById(R.id.btnRemove);
        mImgAvatar.setOnClickListener(this);
        mBtnAddEdit.setOnClickListener(this);
        mBtnRemove.setOnClickListener(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_user);
        init();

        mValue = getIntent().getStringExtra("value");

        if (mValue.equals("add")) {
            mTvTitle.setText("ADD");
            mBtnAddEdit.setText("ADD");
            mBtnRemove.setVisibility(View.GONE);
        } else {
            mUser = getIntent().getBundleExtra("object").getParcelable("data");
            mTvTitle.setText("EDIT");
//            mImgAvatar.setImageURI(Uri.parse(mUser.getImage()));
            Picasso.with(this).load(mUser.getImage()).into(mImgAvatar);
            Toast.makeText(this,"cccc" + mUser.getImage(), Toast.LENGTH_SHORT).show();
            Log.d("bbbbb", "onCreate: " + mUser.getImage());
            mEdtName.setText(mUser.getName());
            mEdtAge.setText(mUser.getAge());
            mEdtGender.setText(mUser.getGender());
            mBtnAddEdit.setText("EDIT");
            mBtnRemove.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgAvatar:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Choose a photo"), 5);
                break;
            case R.id.btnAdd:
                if (mValue.equals("add")) { // add
                    if (!path.equals("") && mEdtName.getText().toString() != null && mEdtAge.getText().toString() != null && mEdtAge.getText().toString() != null) {
                        User user = new User();
                        user.setName(mEdtName.getText().toString());
                        user.setAge(mEdtAge.getText().toString());
                        user.setGender(mEdtGender.getText().toString());
                        user.setImage(path);
                        Log.d("111111", "onClick: " + path);
                        mUserDatabase.insertUser(user);
                    }
                } else { // edit
                    User user = new User(mUser.getId(), path, mEdtName.getText().toString(), mEdtAge.getText().toString(), mEdtGender.getText().toString());
                    mUserDatabase.updateUser(user);
                }
                Intent it = new Intent(LayoutAddEditActitvity.this, ListUserActivity.class);
                startActivity(it);
                finish();
                break;
            case R.id.btnRemove:
                Intent i = new Intent(LayoutAddEditActitvity.this, ListUserActivity.class);
                mUserDatabase.deleteUser(mUser.getId());
                startActivity(i);
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ListUserActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5 && resultCode == RESULT_OK && null != data) {
            Uri mSelectedImageUri = data.getData();
            path = mSelectedImageUri.toString();
            if (null != mSelectedImageUri) {
                Picasso.with(this).load(path).into(mImgAvatar);
            }
        }
    }
}
