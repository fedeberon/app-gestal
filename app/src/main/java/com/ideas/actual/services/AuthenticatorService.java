package com.ideas.actual.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.ideas.actual.configuration.MyAccountAuthenticator;

public class AuthenticatorService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        MyAccountAuthenticator authenticator = new MyAccountAuthenticator(this);
        return authenticator.getIBinder();
    }
}
