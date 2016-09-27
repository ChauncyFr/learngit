package com.example.fxr.demo;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_back;
    private TextView tv_title, tv_tip;
    private HashMap<Integer, LoginFragment> fragments = new HashMap<>();
    private int lastLayout, curLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragments();
        initView();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.id_fragment, fragments.get(R.layout.fragment_login));
        transaction.commit();
        curLayout = lastLayout = R.layout.fragment_login;
        initEvent();


    }


    private void replaceFragment(int currentLayout, int replaceLayout) {

        switch (replaceLayout) {
            case R.layout.fragment_login:
                tv_tip.setVisibility(View.VISIBLE);
                tv_tip.setText("忘记密码?");
                tv_title.setText("登录");
                break;
            case R.layout.fragment_forgetpass:
                tv_tip.setVisibility(View.GONE);
                tv_title.setText("忘记密码");
                break;
            case R.layout.fragment_register:
                tv_tip.setVisibility(View.VISIBLE);
                tv_tip.setText("登录");
                tv_title.setText("注册");
                break;
            default:
                break;
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        lastLayout = currentLayout;
        transaction.replace(R.id.id_fragment, fragments.get(replaceLayout));
        transaction.commit();
        curLayout = replaceLayout;


    }


    private void initFragments() {
        fragments.put(R.layout.fragment_login, new LoginFragment(R.layout.fragment_login, this));
        fragments.put(R.layout.fragment_forgetpass, new LoginFragment(R.layout.fragment_forgetpass, this));
        fragments.put(R.layout.fragment_setpass, new LoginFragment(R.layout.fragment_setpass, this));
        fragments.put(R.layout.fragment_register, new LoginFragment(R.layout.fragment_register, this));
    }

    private void initEvent() {
        LoginFragment loginFragment = fragments.get(R.layout.fragment_login);
        LoginFragment forgetFragment = fragments.get(R.layout.fragment_forgetpass);
        tv_tip.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        loginFragment.getView(R.id.fl_btn_login).setOnClickListener(this);
        loginFragment.getView(R.id.fl_tv_register).setOnClickListener(this);
        forgetFragment.getView(R.id.ff_btn_next).setOnClickListener(this);


    }

    private void initView() {
        tv_tip = (TextView) findViewById(R.id.top_tv_tip);
        tv_title = (TextView) findViewById(R.id.top_tv_title);
        iv_back = (ImageView) findViewById(R.id.top_iv_back);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.top_iv_back:
                goBack();
                break;
            case R.id.fl_tv_register:
                register();
                break;
            case R.id.fl_btn_login:
                break;
            case R.id.top_tv_tip:
                if (tv_tip.getText().toString().equals("忘记密码?")) {
                    forget_pass();
                } else {
                    gotoLogin();
                }
                break;

            case R.id.ff_btn_next:
                setpass();
                break;
            default:
                break;
        }
    }


    private void forget_pass() {

        tv_tip.setVisibility(View.GONE);
        tv_title.setText("忘记密码");
        replaceFragment(R.layout.fragment_login, R.layout.fragment_forgetpass);

    }

    private void register() {

        tv_tip.setText("登录");
        tv_title.setText("注册");

        replaceFragment(R.layout.fragment_login, R.layout.fragment_register);


    }

    private void setpass() {
        tv_tip.setVisibility(View.GONE);
        tv_title.setText("设置密码");
        replaceFragment(R.layout.fragment_forgetpass, R.layout.fragment_setpass);
    }


    private void gotoLogin() {

        tv_title.setText("登录");
        tv_tip.setText("忘记密码?");
        replaceFragment(R.layout.fragment_register, R.layout.fragment_login);
        AppCompatCheckBox cb = (AppCompatCheckBox) fragments.get(R.layout.fragment_register).getView(R.id.fr_cb_agree);
        cb.setChecked(false);

    }

    private void goBack() {


        switch (curLayout) {
            case R.layout.fragment_login:
                System.exit(0);
                break;
            case R.layout.fragment_register:
                gotoLogin();
                break;
            case R.layout.fragment_forgetpass:
                replaceFragment(R.layout.fragment_forgetpass, R.layout.fragment_login);
                break;
            case R.layout.fragment_setpass:
                replaceFragment(R.layout.fragment_setpass, R.layout.fragment_forgetpass);
                break;
            default:
                break;
        }

    }

}
