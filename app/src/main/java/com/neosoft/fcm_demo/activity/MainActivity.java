package com.neosoft.fcm_demo.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.neosoft.fcm_demo.R;
import com.neosoft.fcm_demo.fcm.MyFirebaseInstanceIdService;
import com.neosoft.fcm_demo.prefrenceUtils.SharedPrefManager;

public class MainActivity extends AppCompatActivity {
    private TextView token;
    private BroadcastReceiver broadcastReceiver;
    private EditText etemail;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        token = (TextView) findViewById(R.id.tvToken);
        etemail =(EditText) findViewById(R.id.et_email);
        register =(Button)findViewById(R.id.btn_reg);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sentTokenToServer();
            }
        });

        //Receiving Borcast msg from MyFirebaseInstanceIdService
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                token.setText(SharedPrefManager.getmInstance(MainActivity.this).getToken());
            }
        };


        if (SharedPrefManager.getmInstance(this).getToken() != null) {
            token.setText(SharedPrefManager.getmInstance(MainActivity.this).getToken());
            Log.e("fcmtoken", SharedPrefManager.getmInstance(this).getToken());

        }

        registerReceiver(broadcastReceiver, new IntentFilter(MyFirebaseInstanceIdService.TOKEN_BROADCAST));

    }
    private void sentTokenToServer(){
        String email = etemail.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            etemail.setError("Field Vacant");
        }else {
            if(SharedPrefManager.getmInstance(this).getToken() != null){
                StringRequest stringRequest =new StringRequest(Request.Method.POST, "",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);

            }else {
                Toast.makeText(this, "Token is not generated", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
