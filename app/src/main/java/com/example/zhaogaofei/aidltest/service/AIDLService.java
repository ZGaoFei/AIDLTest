package com.example.zhaogaofei.aidltest.service;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.zhaogaofei.aidltest.model.IUserManager;
import com.example.zhaogaofei.aidltest.model.UserInfo;

public class AIDLService extends Service {
    private static final String TAG = "AIDLService.class";

    IUserManager.Stub iBinder = new IUserManager.Stub() {
        @Override
        public void getNum(int anInt) throws RemoteException {
            Log.e(TAG, "===" + anInt);
        }

        @Override
        public void getUser(UserInfo userInfo) throws RemoteException {
            Log.e(TAG, "name:" + userInfo.getName());
            Log.e(TAG, "sex:" + userInfo.getSex());
            Log.e(TAG, "age:" + userInfo.getAge());
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }
}
