package com.neosoft.fcm_demo.fcm;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.neosoft.fcm_demo.prefrenceUtils.SharedPrefManager;

/**
 * Created by webwerks1 on 1/12/17.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    private static final String TAG = MyFirebaseInstanceIdService.class.getSimpleName();
    public   static final String TOKEN_BROADCAST ="tokenbroadcast";

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        //Sensing Boardcast Reciever to MainActivity
        getApplicationContext().sendBroadcast(new Intent(TOKEN_BROADCAST));
        storeToken(refreshedToken);
    }

    private void storeToken(String token) {
        SharedPrefManager.getmInstance(getApplicationContext()).storedToken(token);


    }

}
