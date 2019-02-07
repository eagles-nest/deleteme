package com.evans.loopj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private static EditText usernameTxt,passwordTxt;
    private static Button btnLogin;
    private static AsyncHttpClient client = new AsyncHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameTxt=(EditText)findViewById(R.id.usernameTxt);
        passwordTxt=(EditText)findViewById(R.id.passwordTxt);
        btnLogin=(Button)findViewById(R.id.Login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameTxt.getText().toString();
                String password = passwordTxt.getText().toString();
                RequestParams requestParams = new RequestParams();
                requestParams.put("email",username );
                requestParams.put("password", password);
                String loginURL = "http://mcarfix.demoscad.net/api/login";
                client.post(loginURL, requestParams, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Toast.makeText(MainActivity.this, "Failed to login " + responseString, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        Toast.makeText(MainActivity.this, responseString, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
