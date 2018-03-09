package com.example.zhaogaofei.aidltest.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.zhaogaofei.aidltest.R;
import com.example.zhaogaofei.aidltest.service.MessengerService;

public class ClientActivity extends AppCompatActivity {
    private static final String TAG = "ClientActivity.class";

    private Messenger clientMessenger = new Messenger(new ClientHandler());
    private MessageServiceConnect connect = new MessageServiceConnect();
    private IBinder iBinder;

    public static void start(Context ctx) {
        Intent intent = new Intent(ctx, ClientActivity.class);
        ctx.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        initView();

        startMessengerService();
    }

    @Override
    protected void onDestroy() {
        unbindService(connect);
        super.onDestroy();
    }

    private void initView() {
        TextView textView = (TextView) findViewById(R.id.tv_send_message_to_service);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void startMessengerService() {
        Intent intent = new Intent(ClientActivity.this, MessengerService.class);
        bindService(intent, connect, Context.BIND_AUTO_CREATE);
    }

    private void sendMessage() {
        if (iBinder != null && iBinder.isBinderAlive()) {
            Messenger messenger = new Messenger(iBinder);
            Message message = Message.obtain(null, 0);
            Bundle bundle = new Bundle();
            bundle.putString("msg", "i am client!!! give me a message!");
            message.setData(bundle);

            message.replyTo = clientMessenger;

            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * connect service and send message to service
     */
    private class MessageServiceConnect implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iBinder = service;
            Log.e(TAG, "client connected service!");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    private static class ClientHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Log.e(TAG, msg.getData().getString("msg"));
                    break;
            }
            super.handleMessage(msg);
        }
    }

}
