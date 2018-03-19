package com.hz.loginanimate;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mBtnLogin;

    private View progress;

    private View mInputLayout;

    private float mWidth, mHeight;

    private LinearLayout mName, mPsw;

    private boolean isHide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

        mBtnLogin = (TextView) findViewById(R.id.main_btn_login);
        progress = findViewById(R.id.layout_progress);
        mInputLayout = findViewById(R.id.input_layout);
        mName = (LinearLayout) findViewById(R.id.input_layout_name);
        mPsw = (LinearLayout) findViewById(R.id.input_layout_psw);

        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        mWidth = mBtnLogin.getMeasuredWidth();
        mHeight = mBtnLogin.getMeasuredHeight();

        if(isHide){
            isHide = false;
            showAnimator();
        }else{
            isHide = true;
            hideAnimator();
        }

    }

    public void showAnimator(){
        showProgressAnimator();
    }

    private void hideAnimator(){
        hideInputAnimator();
    }

    /**
     * 关闭输入框动画
     */
    private void hideInputAnimator() {
        mName.setVisibility(View.INVISIBLE);
        mPsw.setVisibility(View.INVISIBLE);

        AnimatorSet set = new AnimatorSet();

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mInputLayout,
                "scaleX", 1f, 0.5f);
        set.setDuration(400);
        set.setInterpolator(new AccelerateInterpolator());
        set.playTogether(animator2);
        set.start();
        set.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                progress.setVisibility(View.VISIBLE);
                hideProgressAnimator();
                mInputLayout.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // TODO Auto-generated method stub
            }
        });

    }

    /**
     * 关闭进度动画
     */
    private void hideProgressAnimator() {
        PropertyValuesHolder animator = PropertyValuesHolder.ofFloat("scaleX",
                0.5f, 1f);
        PropertyValuesHolder animator2 = PropertyValuesHolder.ofFloat("scaleY",
                0.5f, 1f);
        ObjectAnimator animator3 = ObjectAnimator.ofPropertyValuesHolder(progress,
                animator, animator2);
        animator3.setDuration(600);
        animator3.setInterpolator(new JellyInterpolator());
        animator3.start();

    }

    /**
     * 显示进度动画
     */
    private void showProgressAnimator(){

        PropertyValuesHolder animator = PropertyValuesHolder.ofFloat("scaleX",
                1f, 0.5f);
        PropertyValuesHolder animator2 = PropertyValuesHolder.ofFloat("scaleY",
                1f, 0.5f);
        ObjectAnimator animator3 = ObjectAnimator.ofPropertyValuesHolder(progress,
                animator, animator2);
        animator3.setDuration(400);
        animator3.setInterpolator(new AccelerateInterpolator());
        animator3.start();

        animator3.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                progress.setVisibility(View.INVISIBLE);
                mInputLayout.setVisibility(View.VISIBLE);
                mName.setVisibility(View.VISIBLE);
                mPsw.setVisibility(View.VISIBLE);

                showInputAnimator();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * 显示输入框动画
     */
    private void showInputAnimator() {
        AnimatorSet set = new AnimatorSet();

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mInputLayout,
                "scaleX", 0f, 1f);
        set.setDuration(600);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(animator2);
        set.start();
        set.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                progress.setVisibility(View.INVISIBLE);
                mName.setVisibility(View.VISIBLE);
                mPsw.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // TODO Auto-generated method stub
            }
        });

    }

}
