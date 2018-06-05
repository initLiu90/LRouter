package com.test.routerdemo;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.lzp.router.api.Router;
import com.lzp.router.api.RouterCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("/demo2/activity2?name=lzp&age=28");
                Router.build(MainActivity.this, uri).create().route(new RouterCallback() {
                    @Override
                    public void onNotFound(String path) {
                        Toast.makeText(MainActivity.this, "onNotFound path=" + path, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
