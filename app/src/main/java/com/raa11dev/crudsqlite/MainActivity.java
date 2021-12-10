package com.raa11dev.crudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LoginView{
    private LoginPresenter presenter;
    private EditText atUsername, atPassword;
    private Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        atUsername = (EditText)findViewById(R.id.username);
        atPassword = (EditText)findViewById(R.id.password);
        btnlogin = (Button) findViewById(R.id.login);

        presenter = new LoginPresenterimp(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login(atUsername.getText().toString(), atPassword.getText().toString());
            }
        });
    }
    @Override
    public void showErrorLogin() {
        Toast.makeText(this, "Username dan Password tidak boleh kosong !", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void successLogin() {
        Toast.makeText(this, "Anda berhasil login", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
    }
    @Override
    public void errorLogin() {
        Toast.makeText(this, "Username dan password salah", Toast.LENGTH_SHORT).show();
    }
}
