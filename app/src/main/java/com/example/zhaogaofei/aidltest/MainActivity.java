package com.example.zhaogaofei.aidltest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.zhaogaofei.aidltest.client.AIDLClientActivity;
import com.example.zhaogaofei.aidltest.client.ClientActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        TextView textView = (TextView) findViewById(R.id.tv_skip_client);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClientActivity.start(MainActivity.this);
            }
        });

        TextView tvAIDL = (TextView) findViewById(R.id.tv_skip_aidl_client);
        tvAIDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AIDLClientActivity.start(MainActivity.this);
            }
        });
    }
}
