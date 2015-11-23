package com.lymno.myfridge.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.Toast;

import com.lymno.myfridge.R;
import com.lymno.myfridge.model.Token;
import com.lymno.myfridge.model.User;
import com.lymno.myfridge.network.RestClient;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {
    @Bind(R.id.login_login_btn)
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.login_toolbar);
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.login_login_btn)
    public void login() {
        User user = new User("password", "Admin", "secret");
        RestClient.get().createTask(user, new Callback<Token>() {
            @Override
            public void success(Token token, Response response) {
                Toast.makeText(LoginActivity.this, token.getAccessToken(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(LoginActivity.this, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


}
