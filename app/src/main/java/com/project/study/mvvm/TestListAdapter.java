package com.project.study.mvvm;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.study.mvvm.databinding.ListItemBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunmin on 2017-11-26.
 */

public class TestListAdapter extends RecyclerView.Adapter<TestListAdapter.Holder>{
    List<User> data = new ArrayList<>();

    public void setData(List<User> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        User user = data.get(position);
        holder.binding.setUser(user);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        ListItemBinding binding;
        public Holder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
