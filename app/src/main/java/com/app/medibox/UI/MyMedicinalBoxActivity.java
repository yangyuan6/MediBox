package com.app.medibox.UI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.app.medibox.R;
import com.app.medibox.UI.fragment.MyFragment;
import com.app.medibox.bean.Medicinal;
import com.google.gson.Gson;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;

import java.util.List;

public class MyMedicinalBoxActivity extends AppCompatActivity implements View.OnClickListener {
    private MyFragment f1;
    private MyFragment f2;
    private MyFragment f3;
    private MyFragment f4;

    private int REQUEST_CODE_SCAN = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicinal);
        initFragment1();
        View btn_sao = findViewById(R.id.btn_sao);
        btn_sao.setOnClickListener(this);
        View btn_medicinal1= findViewById(R.id.btn_medicinal1);
        btn_medicinal1.setOnClickListener(this);
        View btn_medicinal2= findViewById(R.id.btn_medicinal2);
        btn_medicinal2.setOnClickListener(this);
        View btn_medicinal3= findViewById(R.id.btn_medicinal3);
        btn_medicinal3.setOnClickListener(this);
        View btn_medicinal4= findViewById(R.id.btn_medicinal4);
        btn_medicinal4.setOnClickListener(this);
        View btn_add_medicine=findViewById(R.id.btn_add_medicine);
        btn_add_medicine.setOnClickListener(this);

    }

    private void initFragment1() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (f1 == null) {
            f1 = new MyFragment();
        }
        transaction.setCustomAnimations(R.anim.slide_right_in,R.anim.slide_left_out);
        transaction.replace(R.id.main_frame_layout, f1);
        hideFragment(transaction);
        transaction.show(f1);
        transaction.commit();
    }
    private void initFragment2() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (f2 == null) {
            f2 = new MyFragment();
        }
        transaction.setCustomAnimations(R.anim.slide_right_in,R.anim.slide_left_out);
        transaction.replace(R.id.main_frame_layout, f2);
        hideFragment(transaction);
        transaction.show(f2);
        transaction.commit();
    }
    private void initFragment3() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (f3 == null) {
            f3 = new MyFragment();
        }
        transaction.setCustomAnimations(R.anim.slide_right_in,R.anim.slide_left_out);
        transaction.replace(R.id.main_frame_layout, f3);
        hideFragment(transaction);
        transaction.show(f3);
        transaction.commit();
    }
    private void initFragment4() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (f4 == null) {
            f4 = new MyFragment();

        }
        transaction.setCustomAnimations(R.anim.slide_right_in,R.anim.slide_left_out);
        transaction.replace(R.id.main_frame_layout, f4);
        hideFragment(transaction);
        transaction.show(f4);
        transaction.commit();
    }

    //隐藏所有的fragment
    private void hideFragment(FragmentTransaction transaction) {
        if (f1 != null) {
            transaction.hide(f1);
        }
        if (f2 != null) {
            transaction.hide(f2);
        }
        if (f3 != null) {
            transaction.hide(f3);
        }
        if (f4 != null) {
            transaction.hide(f4);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_medicinal1:{
                initFragment1();
                break;
            }
            case R.id.btn_medicinal2:{
                initFragment2();
                break;
            }
            case R.id.btn_medicinal3:{
                initFragment3();
                break;
            }
            case R.id.btn_medicinal4:{
                initFragment4();
                break;
            }
            case R.id.btn_add_medicine:{
                Intent intent=new Intent(MyMedicinalBoxActivity.this,AddMedicineActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.btn_sao: {
                AndPermission.with(this)
                        .permission(Permission.CAMERA, Permission.READ_EXTERNAL_STORAGE)
                        .onGranted(new Action() {
                            @Override
                            public void onAction(List<String> permissions) {
                                Intent intent = new Intent(MyMedicinalBoxActivity.this, CaptureActivity.class);

                                /*ZxingConfig是配置类  可以设置是否显示底部布局，闪光灯，相册，是否播放提示音  震动等动能
                                 * 也可以不传这个参数
                                 * 不传的话  默认都为默认不震动  其他都为true
                                 * */

                                ZxingConfig config = new ZxingConfig();
                                config.setPlayBeep(true);
                                config.setShake(true);
                                config.setShowFlashLight(false);
                                intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);
                                startActivityForResult(intent, REQUEST_CODE_SCAN);
                            }
                        })
                        .onDenied(new Action() {
                            @Override
                            public void onAction(List<String> permissions) {
                                Uri packageURI = Uri.parse("package:" + getPackageName());
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                startActivity(intent);

                                Toast.makeText(MyMedicinalBoxActivity.this, "没有权限无法扫描呦", Toast.LENGTH_LONG).show();
                            }
                        }).start();
                break;
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra(Constant.CODED_CONTENT);
                Medicinal medicinal=new Gson().fromJson(content, Medicinal.class);
                if(medicinal!=null){
                    Intent intent=new Intent(MyMedicinalBoxActivity.this,AddMedicineActivity.class);
                    intent.putExtra("medicinal",medicinal);
                    startActivity(intent);
                }else{
                    Toast.makeText(this,"扫描失败",Toast.LENGTH_LONG);
                }

            }
        }
    }
}
