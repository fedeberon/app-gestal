package com.ideas.actual;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.ideas.actual.configuration.RetrofitServiceFactory;
import com.ideas.actual.model.User;
import com.ideas.actual.services.AuthenticationService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartActivity extends AppCompatActivity {

    private AuthenticationService authenticationService = RetrofitServiceFactory.createService(AuthenticationService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        onSignIn();
    }


    private void onSignIn() {
        AccountManager accountManager = AccountManager.get(this);
        Account account = getAccount(accountManager);

        if (account == null) {

            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, "com.actual.auth");
            intent.putExtra("addAccount", true);

            startActivityForResult(intent, 22);
            Toast.makeText(this, "No hay registros de autenticacion", Toast.LENGTH_LONG).show();
            return;
        }

        else accountManager.getAuthToken(
                                            account,     // Account retrieved using getAccountsByType()
                                            "com.actual.auth",            // Auth scope
                                            null,                        // Authenticator-specific options
                                            this,                           // Your activity
                                            new OnTokenAcquired(),          // Callback called when a token is successfully acquired
                                            null);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        startActivity(new Intent(StartActivity.this, HomeActivity.class));
    }

    private Account getAccount(AccountManager accountManager){
        if(this.isAccountExist(accountManager)) {
            Account[] accounts = accountManager.getAccountsByType("com.actual.auth");
            return accounts[0];
        }
        else return null;
    }

    private boolean isAccountExist(AccountManager accountManager) {
        Account[] accounts;
        if (accountManager != null) {
            accounts = accountManager.getAccounts();
            for (Account account : accounts) {
                if (account.type.intern().equals("com.actual.auth")) {
                    return true;
                }
            }
        }
        return false;
    }

    private class OnTokenAcquired implements AccountManagerCallback<Bundle> {

        @Override
        public void run(AccountManagerFuture<Bundle> result) {
            try {
                Bundle bundle = result.getResult();
                String token = bundle.getString(AccountManager.KEY_AUTHTOKEN);
                this.addTokenInHeader(token);

                /*Intent launch = (Intent) result.getResult().get(AccountManager.KEY_INTENT);*/
                Intent launch = new Intent(StartActivity.this, HomeActivity.class);
                startActivityForResult(launch, 200);

            } catch (Exception e) {
                Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, "com.actual.auth");
                intent.putExtra("addAccount", true);
                startActivityForResult(intent, 22);
                return;
            }
        }

        private void addTokenInHeader(String token){
            authenticationService = RetrofitServiceFactory.createService(AuthenticationService.class, token);
            Call<User> call = authenticationService.me();
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.isSuccessful()){
                        User user = response.body();
                        Log.v("Auth Actual app", "Authenticated " +  user.getUsername());
                    }

                    else {
                        startActivity(new Intent(StartActivity.this, LoginActivity.class));
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.v("Auth Actual app", "Authenticated Fail " +  t.getMessage());
                }
            });
        }
    }

}
