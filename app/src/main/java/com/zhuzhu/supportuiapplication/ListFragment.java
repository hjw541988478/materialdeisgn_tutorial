package com.zhuzhu.supportuiapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/9/8.
 */
public class ListFragment extends Fragment {

    public List<String> data = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("tag", "onCreate");
        if (getArguments() != null) {
            data = getArguments().getStringArrayList("data");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("tag", "onCreateView");
        View root = inflater.inflate(R.layout.fragment_list, container, false);
        final RecyclerViewListAdapter adapter = new RecyclerViewListAdapter(data);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.id_list);
        final SwipeRefreshLayout swipeRefresh = (SwipeRefreshLayout) root.findViewById(R.id.id_refresh);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.data.add(0, "onRefresh");
                adapter.notifyDataSetChanged();
                swipeRefresh.setRefreshing(false);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        return root;
    }


    class RecyclerViewListAdapter extends RecyclerView.Adapter<RecyclerViewListAdapter.MyViewHolder> {

        private List<String> data = new ArrayList<>();

        public RecyclerViewListAdapter(List<String> data) {
            this.data.clear();
            this.data.addAll(data);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_fragment_list, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.itemText.setText(data.get(position));
            holder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailActivity.class);
                    intent.putExtra("title", data.get(position));
                    v.getContext().startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            @Bind(R.id.id_item_text)
            TextView itemText;

            View rootView;

            public MyViewHolder(View itemView) {
                super(itemView);
                rootView = itemView;
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
