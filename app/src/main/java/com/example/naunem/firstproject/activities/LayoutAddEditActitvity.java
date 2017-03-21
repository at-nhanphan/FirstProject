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

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.models.User;
import com.example.naunem.firstproject.models.UserDatabase;

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
    private Button mBtnEdit;
    private Button mBtnRemove;
    private String mValue;
    private User mUser;
    private UserDatabase mUserDatabase = new UserDatabase(this);
    private Uri mSelectedImageUri;

    private void init() {
        mTvTitle = (TextView) findViewById(R.id.tvTitle);
        mImgAvatar = (ImageView) findViewById(R.id.imgAvatar);
        mEdtName = (EditText) findViewById(R.id.edtName);
        mEdtAge = (EditText) findViewById(R.id.edtAge);
        mEdtGender = (EditText) findViewById(R.id.edtGender);
        mBtnAddEdit = (Button) findViewById(R.id.btnAdd);
        mBtnEdit = (Button) findViewById(R.id.btnEdit);
        mBtnRemove = (Button) findViewById(R.id.btnRemove);
        mImgAvatar.setOnClickListener(this);
        mBtnAddEdit.setOnClickListener(this);
        mBtnEdit.setOnClickListener(this);
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
            mBtnEdit.setVisibility(View.GONE);
        } else {
            mUser = getIntent().getBundleExtra("object").getParcelable("data");
            mTvTitle.setText("EDIT");
            mImgAvatar.setImageURI(Uri.parse(mUser.getImage()));
            mEdtName.setText(mUser.getName());
            mEdtAge.setText(mUser.getAge());
            mEdtGender.setText(mUser.getGender());
            mBtnAddEdit.setText("EDIT");
            mBtnRemove.setVisibility(View.VISIBLE);
            mBtnAddEdit.setVisibility(View.GONE);
            mBtnEdit.setVisibility(View.VISIBLE);
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
                    if (mEdtName.getText().toString() != null && mEdtAge.getText().toString() != null && mEdtAge.getText().toString() != null) {
                        User user = new User();
                        user.setName(mEdtName.getText().toString());
                        user.setAge(mEdtAge.getText().toString());
                        user.setGender(mEdtGender.getText().toString());
                        user.setImage(mSelectedImageUri.getPath());
                        mUserDatabase.insertUser(user);
                        mEdtName.setText("");
                        mEdtAge.setText("");
                        mEdtGender.setText("");
                        Intent itList = new Intent(LayoutAddEditActitvity.this, ListUserActivity.class);
                        startActivity(itList);
                        finish();
                        Log.d("path", "onClick: " + user.getImage());
                    }
//                } else { // edit
//                    User user = new User(mSelectedImageUri.toString(), mEdtName.getText().toString(), mEdtAge.getText().toString(), mEdtGender.getText().toString());
//                    mUserDatabase.updateUser(user);
//                    Log.d("name", "onClick: " + mEdtName.getText().toString());
//                    itList = new Intent(LayoutAddEditActitvity.this, ListUserActivity.class);
////                    finish();
                }

                break;
            case R.id.btnEdit:
                User user = new User(mUser.getId(), mUser.getImage(), mEdtName.getText().toString(), mEdtAge.getText().toString(), mEdtGender.getText().toString());
                mUserDatabase.updateUser(user);
                Log.d("name", "onClick: " + mEdtName.getText().toString());
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
            mSelectedImageUri = data.getData();
            if (null != mSelectedImageUri) {
                mImgAvatar.setImageURI(mSelectedImageUri);
            }
        }
    }
}
