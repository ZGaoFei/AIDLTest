package com.example.zhaogaofei.aidltest.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.zhaogaofei.aidltest.R;
import com.example.zhaogaofei.aidltest.model.IUserManager;
import com.example.zhaogaofei.aidltest.model.UserInfo;
import com.example.zhaogaofei.aidltest.service.AIDLService;

public class AIDLClientActivity extends AppCompatActivity {

    public static void start(Context ctx) {
        Intent intent = new Intent(ctx, AIDLClientActivity.class);
        ctx.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidlclient);

        initView();

        Intent intent = new Intent(this, AIDLService.class);
        bindService(intent, aidlServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(aidlServiceConnection);
        super.onDestroy();
    }

    private void initView() {
        TextView textView = (TextView) findViewById(R.id.tv_send_message_to_aidl_service);
        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            }
        });
    }

    private ServiceConnection aidlServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IUserManager remoteService = IUserManager.Stub.asInterface(service);
            UserInfo userInfo = new UserInfo();
            userInfo.setName("xiaofei");
            userInfo.setSex("man");
            userInfo.setAge(18);

            try {
                remoteService.getNum(0);
                remoteService.getUser(userInfo);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

}
