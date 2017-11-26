package com.project.study.mvvm;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.project.study.mvvm.databinding.ActivityListBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    TestListAdapter adapter;
    ActivityListBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list);
        adapter = new TestListAdapter();
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataSetting();
    }
    private void dataSetting() {
        String[] ids = {"dmsals", "merrong", "juwon", "haha", "zico"};
        String[] pws = {"fdsifjaisfdj", "foasdjfidjsiof", "sidajfoijsfoj","fijsaojfjsofasadf"};
        List<User> data = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            User user = new User();
            user.id = ids[i%5];
            user.password = pws[i%4];
            data.add(user);
        }
        adapter.setData(data);
    }
}
