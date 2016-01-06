package cn.edu.nuc.designdemo;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SwipeDismissBehavior.OnDismissListener, View.OnClickListener {

    private TextView textView;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.main_text);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
        textView.setOnClickListener(this);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) textView.getLayoutParams();
        //这是一个删除（还有其他的行为）的行为，如果想设定自己的删除效果，可以自定义，需要继承SwipeDismissBehavior
        SwipeDismissBehavior behavior = new SwipeDismissBehavior();
        behavior.setListener(this);
        params.setBehavior(behavior);
        //使用的V7包的Toolbar，但是需要在style.xml设置主题为Theme.AppCompat.Light.NoActionBar，
        // 就是将actionbar 取消，否则会与Toolbar发生冲突
        Toolbar toolbar = (Toolbar) findViewById(R.id.toobar);
        //把Toolbar当做ActionBar使用
        setSupportActionBar(toolbar);
        //在ToolBar上显示返回键，和在ActionBar上设置返回键一样的打代码
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    /**
     * 消失的时候
     * @param view
     */
    @Override
    public void onDismiss(final View view) {
        //真实 的删除一个View
        ViewGroup parent = (ViewGroup)view.getParent();
        parent.removeView(view);
        ViewCompat.setAlpha(view, 1);
        //ViewCompat.setTranslationX(view,0);
        //Snackbar和Toast相似,如果View是CoordinatorLayout的话，自带滑动删除
        Snackbar.make(coordinatorLayout,"删除一个Text",Snackbar.LENGTH_LONG).setAction("撤销", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //之前删除的View，在添加回来
                coordinatorLayout.addView(view);
            }
        }).show();
        //view.setVisibility(View.GONE);//隐藏一个控件，没有真实 的删除
        //Toast.makeText(this, "删除了一个TextView", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDragStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this,CoordinatorActivity.class));
    }
}
