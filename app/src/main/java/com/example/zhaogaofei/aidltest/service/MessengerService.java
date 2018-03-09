package com.example.zhaogaofei.aidltest.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;


public class MessengerService extends Service {
    private static final String TAG = "MessengerService.class";

    private MessengerHandler handler = new MessengerHandler();
    private Messenger messenger = new Messenger(handler);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:// get client message and then send message to client
                    Log.e(TAG, msg.getData().getString("msg"));

                    Messenger messenger = msg.replyTo;
                    Message message = Message.obtain(null, 1);
                    Bundle bundle = new Bundle();
                    bundle.putString("msg", "i am service!!!");
                    message.setData(bundle);
                    try {
                        messenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    }
}
