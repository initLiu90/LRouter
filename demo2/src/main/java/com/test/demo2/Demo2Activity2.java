package com.test.demo2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.lzp.router.annotation.Router;

@Router(path = "/demo2/activity2")
public class Demo2Activity2 extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = new View(this);
        view.setBackgroundColor(Color.BLUE);
        setContentView(view);
//        String name = getIntent().getExtras().getString("name");
//        String age = getIntent().getExtras().getString("age");
//        Log.e("Test", "name=" + name + ",age=" + age);
    }
}
