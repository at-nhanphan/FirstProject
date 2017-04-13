package com.example.naunem.firstproject;

import com.example.naunem.firstproject.interfaces.SOService;

/**
 * ApiUtils class
 * Created by naunem on 05/04/2017.
 */

public class ApiUtils {
    private static final String BASE_URL = "https://api.stackexchange.com/2.2/";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}
