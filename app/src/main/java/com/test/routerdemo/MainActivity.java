package com.test.routerdemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ComponentName;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.lzp.router.api.Router;
import com.lzp.router.api.RouterCallback;
import com.lzp.router.api.logistics.Route;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("/demo2/activity2?name=lzp&age=28");
                Object resutl = Router.build(MainActivity.this, uri).create().route(new RouterCallback() {
                    @Override
                    public void onNotFound(String path) {
                        Toast.makeText(MainActivity.this, "onNotFound path=" + path, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onHandled() {

                    }

                    @Override
                    public void onError(String errorMsg) {

                    }
                });
            }
        });

        findViewById(R.id.btn_startservice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.build(MainActivity.this, "/demo1/service").create().route();
            }
        });

        findViewById(R.id.btn_bindservice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.build(MainActivity.this, "/demo1/service")
                        .setServiceConnection(new ServiceConnection() {
                            @Override
                            public void onServiceConnected(ComponentName name, IBinder service) {

                            }

                            @Override
                            public void onServiceDisconnected(ComponentName name) {

                            }
                        })
                        .create()
                        .route();
            }
        });

        findViewById(R.id.btn_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = (Fragment) Router.build(MainActivity.this, "/demo1/fragment").create().route();
                FragmentManager manager = MainActivity.this.getFragmentManager();
                manager.beginTransaction().add(R.id.content,fragment,"test").commit();
            }
        });

        findViewById(R.id.btn_fragmentv4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.Fragment fragment = (android.support.v4.app.Fragment) Router.build(MainActivity.this, "/demo1/fragmentv4").create().route();
                android.support.v4.app.FragmentManager manager = MainActivity.this.getSupportFragmentManager();
                manager.beginTransaction().add(R.id.content,fragment,"testv4").commit();
            }
        });
    }
}
