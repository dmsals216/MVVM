package com.project.study.mvvm;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.project.study.mvvm.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        User user = new User();
        binding.setUser(user);
        user.id = "아이디";
        user.password = "비밀번호";
    }

    public void goList(View view) {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }


}
