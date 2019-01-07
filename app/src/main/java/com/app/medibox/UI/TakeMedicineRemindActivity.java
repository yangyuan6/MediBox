package com.app.medibox.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.app.medibox.R;
import com.app.medibox.UI.fragment.TakeMedicineRemindFragment;
import com.app.medibox.bean.Remind;

public class TakeMedicineRemindActivity extends AppCompatActivity implements View.OnClickListener {
    private TakeMedicineRemindFragment f1;
    private TakeMedicineRemindFragment f2;
    private TakeMedicineRemindFragment f3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_medicine_remind);
        initFragment1();
        View btn_sao= findViewById(R.id.sao);
        btn_sao.setOnClickListener(this);
        View btn_remind1= findViewById(R.id.btn_remind1);
        btn_remind1.setOnClickListener(this);
        View btn_remind2= findViewById(R.id.btn_remind2);
        btn_remind2.setOnClickListener(this);
        View btn_remind3= findViewById(R.id.btn_remind3);
        btn_remind3.setOnClickListener(this);
        View btn_add_remind= findViewById(R.id.btn_add_remind);
        btn_add_remind.setOnClickListener(this);
    }
    private void initFragment1(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(f1 == null){
            f1 = new TakeMedicineRemindFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("tag",1);
            f1.setArguments(bundle);
        }
        transaction.setCustomAnimations(R.anim.slide_right_in,R.anim.slide_left_out);
        transaction.replace(R.id.main_frame_layout,f1);
        hideFragment(transaction);
        transaction.show(f1);
        transaction.commit();
    }
    private void initFragment2(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(f2 == null){
            f2 = new TakeMedicineRemindFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("tag",2);
            f2.setArguments(bundle);
        }
        transaction.setCustomAnimations(R.anim.slide_right_in,R.anim.slide_left_out);
        transaction.replace(R.id.main_frame_layout,f2);
        hideFragment(transaction);
        transaction.show(f2);
        transaction.commit();
    }
    private void initFragment3(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(f3 == null){
            f3 = new TakeMedicineRemindFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("tag",3);
            f3.setArguments(bundle);
        }
        transaction.setCustomAnimations(R.anim.slide_right_in,R.anim.slide_left_out);
        transaction.replace(R.id.main_frame_layout,f3);
        hideFragment(transaction);
        transaction.show(f3);
        transaction.commit();
    }
    //隐藏所有的fragment
    private void hideFragment(FragmentTransaction transaction){
        if(f1 != null){
            transaction.hide(f1);
        }
        if(f2 != null){
            transaction.hide(f2);
        }
        if(f3 != null){
            transaction.hide(f3);
        }
    }
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.sao:{
                intent = new Intent(this, TakeMedicineRemindActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_remind1:{
                initFragment1();
                break;
            }
            case R.id.btn_remind2:{
                initFragment2();
                break;
            }
            case R.id.btn_remind3:{
                initFragment3();
                break;
            }
            case R.id.btn_add_remind:{
                intent=new Intent(this,AddRemindActivity.class);
                startActivityForResult(intent,100);
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==100){
            Remind remind=(Remind) data.getSerializableExtra("remind");
        }

    }
}
