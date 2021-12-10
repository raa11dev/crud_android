package com.raa11dev.crudsqlite;
import android.text.TextUtils;

public class LoginPresenterimp implements LoginPresenter {
    private LoginView loginView;
    public LoginPresenterimp(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void login(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            loginView.showErrorLogin();
        } else {
            if (username.equals("admin") && password.equals("admin")) {
                loginView.successLogin();
            } else {
                loginView.errorLogin();
            }
        }
    }

}
