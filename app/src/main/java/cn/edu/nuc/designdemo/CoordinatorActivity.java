package cn.edu.nuc.designdemo;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CoordinatorActivity extends AppCompatActivity implements View.OnClickListener {
    private CoordinatorLayout coordinator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(String.format("第%02d条", i));
        }
        recyclerView.setAdapter(new MyAdapter(this, list));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        View button = findViewById(R.id.btn);
        button.setOnClickListener(this);
        coordinator = (CoordinatorLayout) findViewById(R.id.coordinator);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.coor, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        Snackbar.make(coordinator, "点击事件", Snackbar.LENGTH_LONG).show();
//        startActivity(new Intent(this, TextActivity.class));
    }
}
