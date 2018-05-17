package com.test.demo1;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lzp.router.annotation.Router;

@Router(path = "/demo1/activity2")
public class Demo1Activity2 extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = new View(this);
        view.setBackgroundColor(Color.RED);
        setContentView(view);
    }
}
