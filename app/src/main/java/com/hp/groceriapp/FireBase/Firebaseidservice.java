package com.hp.groceriapp.FireBase;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static android.content.ContentValues.TAG;

public class Firebaseidservice extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {

        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e(TAG, "Refreshed token: " + refreshedToken);
        System.out.println("+++++++++++++++++++++++++++++++++++++"+refreshedToken);




        SharedPreferences sharedpreferencesDeviceid = getApplicationContext().getSharedPreferences("sharedpreferencesDeviceid", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferencesDeviceid.edit();

        editor.putString("DeviceID", refreshedToken);
        Toast.makeText(getApplicationContext(), ""+refreshedToken, Toast.LENGTH_SHORT).show();

        editor.commit();
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken) {


    }
}
