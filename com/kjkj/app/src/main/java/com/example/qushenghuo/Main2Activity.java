package com.example.qushenghuo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    //定义Fragment对象
    private Fragment fragment_1,fragment_2,fragment_3,fragment_4,nowFragment;
    //定义底部标签
    private TextView tab_1,tab_2,tab_3,tab_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //初始化界面
        initUI();
    }

    /**
     * 初始化UI界面
     */
    private void initUI(){
        tab_1 = (TextView) findViewById(R.id.tv_tab1);
        tab_2 = (TextView) findViewById(R.id.tv_tab2);
        tab_3 = (TextView) findViewById(R.id.tv_tab3);
        tab_4 = (TextView) findViewById(R.id.tv_tab4);
        //设置底部tab变化
        tab_4.setBackgroundColor(Color.RED);
        tab_2.setBackgroundColor(Color.WHITE);
        tab_3.setBackgroundColor(Color.WHITE);
        tab_1.setBackgroundColor(Color.WHITE);
        //为底部标签设置点击事件
        tab_1.setOnClickListener(this);
        tab_2.setOnClickListener(this);
        tab_3.setOnClickListener(this);
        tab_4.setOnClickListener(this);

        //显示第一个Fragment
        showFragment2();
    }

    //点击事件
    @Override
    public void onClick(View view) {

        if (view.getId()==R.id.tv_tab1){
            //第一个标签被点击
            showFragment1();
            //Intent intent = new Intent(this, NewsActivity.class);
            //intent.setClass(com.example.qushenghuo.Main2Activity.this, NewsActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
            //startActivity(intent);
        }else if (view.getId()== R.id.tv_tab2){
            //第二个标签被点击
            showFragment2();
            //Intent intent = new Intent(this, LearnActivity.class);
            //intent.setClass(com.example.qushenghuo.Main2Activity.this, LearnActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
            //startActivity(intent);
        }else if (view.getId()==R.id.tv_tab3){
            //第三个标签被点击
            showFragment3();
            //Intent intent = new Intent(this, InformationActivity.class);
            //intent.setClass(com.example.qushenghuo.Main2Activity.this, InformationActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
            //startActivity(intent);
        }else if (view.getId()==R.id.tv_tab4){
            //第四个标签被点击
            showFragment4();
            Intent intent = new Intent(this, MyActivity.class);
            intent.setClass(com.example.qushenghuo.Main2Activity.this, MyActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
            startActivity(intent);
        }

    }
    /**
     *第一个标签被点击
     */
    private void showFragment1(){
        //开启事务，Fragment的切换由事务控制
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //判断Fragment是否为空
        if (fragment_1==null){
            //fragment_1 = new NewsActivity();
            //添加Fragment到事务
            Intent intent = new Intent(this, NewsActivity.class);
            startActivity(intent);
            //transaction.add(R.id.content_layout,fragment_1);
        }
        //隐藏所有的Fragment
        hideAllFragment(transaction);
        //显示Fragment
        transaction.show(fragment_1);
        //记录Fragment
        nowFragment = fragment_1;
        //提交事务
        transaction.commit();
        //设置底部tab变化
        tab_1.setBackgroundColor(Color.RED);
        tab_2.setBackgroundColor(Color.WHITE);
        tab_3.setBackgroundColor(Color.WHITE);
        tab_4.setBackgroundColor(Color.WHITE);
    }

    /**
     *第二个标签被点击
     */
    private void showFragment2(){
        //开启事务，Fragment的切换由事务控制
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //判断Fragment是否为空
        if (fragment_2==null){
            fragment_2 = new LearnActivity();
            //添加Fragment到事务
            transaction.add(R.id.content_layout,fragment_2);
        }
        //隐藏所有的Fragment
        hideAllFragment(transaction);
        //显示Fragment
        transaction.show(fragment_2);
        //记录Fragment
        nowFragment = fragment_2;
        //提交事务
        transaction.commit();
        //设置底部tab变化
        tab_1.setBackgroundColor(Color.WHITE);
        tab_2.setBackgroundColor(Color.RED);
        tab_3.setBackgroundColor(Color.WHITE);
        tab_4.setBackgroundColor(Color.WHITE);
    }

    /**
     *第三个标签被点击
     */
    private void showFragment3(){
        //开启事务，Fragment的切换由事务控制
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //判断Fragment是否为空
        if (fragment_3==null){
            fragment_3 = new InformationActivity();
            //添加Fragment到事务
            transaction.add(R.id.content_layout,fragment_3);
        }
        //隐藏所有的Fragment
        hideAllFragment(transaction);
        //显示Fragment
        transaction.show(fragment_3);
        //记录Fragment
        nowFragment = fragment_3;
        //提交事务
        transaction.commit();
        //设置底部tab变化
        tab_1.setBackgroundColor(Color.WHITE);
        tab_2.setBackgroundColor(Color.WHITE);
        tab_3.setBackgroundColor(Color.RED);
        tab_4.setBackgroundColor(Color.WHITE);
    }
    private void showFragment4(){
        //开启事务，Fragment的切换由事务控制
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //判断Fragment是否为空
        if (fragment_4==null){
            fragment_4 = new InformationActivity();

            //添加Fragment到事务
            //transaction.add(R.id.content_layout,fragment_4);
            //Intent intent = new Intent(this, MyActivity.class);
            //startActivity(intent);
        }
        //隐藏所有的Fragment
        hideAllFragment(transaction);
        //显示Fragment
        transaction.show(fragment_4);
        //记录Fragment
        nowFragment = fragment_4;
        //提交事务
        transaction.commit();
        //设置底部tab变化
        tab_1.setBackgroundColor(Color.WHITE);
        tab_2.setBackgroundColor(Color.WHITE);
        tab_3.setBackgroundColor(Color.WHITE);
        tab_4.setBackgroundColor(Color.RED);
    }

    /**
     * 隐藏所有的Fragment
     */
    private void hideAllFragment(FragmentTransaction transaction){
        if (fragment_1!=null){
            transaction.hide(fragment_1);
        }
        if (fragment_2!=null){
            transaction.hide(fragment_2);
        }
        if (fragment_3!=null){
            transaction.hide(fragment_3);
        }
        if (fragment_4!=null){
            transaction.hide(fragment_4);
        }
    }

    public Fragment getNowFragment() {
        return nowFragment;
    }

    public void setNowFragment(Fragment nowFragment) {
        this.nowFragment = nowFragment;
    }
}
