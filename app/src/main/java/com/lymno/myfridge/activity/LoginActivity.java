package com.lymno.myfridge.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
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
    Button manualLoginBtn;
    @Bind(R.id.login_login_manual_btn)
    Button login_btn;
    @Bind(R.id.login_email)
    EditText email;
    @Bind(R.id.login_password)
    EditText password;
    @Bind(R.id.login_toolbar)
    Toolbar toolbar;

    String tokenKey = "com.lymno.myfridge.activity.token";
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        settings = this.getSharedPreferences(
                "com.lymno.myfridge.activity", Context.MODE_PRIVATE);

        String token = settings.getString(tokenKey, "");
        if (!token.isEmpty()) {
            Intent intent = new Intent(LoginActivity.this, Drawer.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

    }

    @OnClick(R.id.login_login_btn)
    public void login() {
        User user = new User("Admin", "djkool");
        RestClient.get().auth(user, new Callback<Token>() {
            @Override
            public void success(Token token, Response response) {
                Toast.makeText(LoginActivity.this, token.getAccessToken(), Toast.LENGTH_LONG).show();
                settings.edit().putString(tokenKey, token.getAccessToken()).apply();
                Intent intent = new Intent(LoginActivity.this, Drawer.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(LoginActivity.this, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
