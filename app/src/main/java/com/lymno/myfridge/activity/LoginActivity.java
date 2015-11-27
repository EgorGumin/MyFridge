package com.lymno.myfridge.activity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.login_login_btn)
    public void login() {
        User user = new User("Admin", "secret");
        RestClient.get().auth(user, new Callback<Token>() {
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
