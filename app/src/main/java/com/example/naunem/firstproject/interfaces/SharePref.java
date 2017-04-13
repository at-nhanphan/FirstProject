package com.example.naunem.firstproject.interfaces;

import com.example.naunem.firstproject.R;

import org.androidannotations.annotations.sharedpreferences.DefaultRes;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * SharePref interface
 * Created by naunem on 12/04/2017.
 */
@SharedPref
public interface SharePref {
    @DefaultRes(R.string.username)
    String username();

    @DefaultRes(R.string.password)
    String password();
}
