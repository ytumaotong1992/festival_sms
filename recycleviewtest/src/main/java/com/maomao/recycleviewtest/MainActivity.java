package com.maomao.recycleviewtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private List<String> mDatas;
    private SimpleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatas();
        initViews();
        mAdapter = new com.maomao.recycleviewtest.SimpleAdapter(this, mDatas);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new SimpleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this , position+" " , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this , position+" " , Toast.LENGTH_LONG).show();
            }
        });

        //设置布局管理
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置item分隔线
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this , DividerItemDecoration.VERTICAL_LIST));
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recycleview);

    }

    private void initDatas() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i <= 'Z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.action_add:

                mAdapter.addData(1);
                // mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL));
                break;
            case R.id.action_delete:
                mAdapter.deleteDate(1);
                // mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL));
                break;

            case R.id.action_gridview:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
                break;

            case R.id.action_listview:

                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.action_hor_gridview:

                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL));
                break;
            case R.id.action_staggered:
                Intent intent = new Intent(this, StaggeredGridLayoutActivity.class);
                startActivity(intent);
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}