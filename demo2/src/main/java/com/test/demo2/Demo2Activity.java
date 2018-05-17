package com.test.demo2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lzp.router.annotation.Router;

@Router(path = "/demo2/activity1")
public class Demo2Activity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = new View(this);
        view.setBackgroundColor(Color.YELLOW);
        setContentView(view);
    }
}
