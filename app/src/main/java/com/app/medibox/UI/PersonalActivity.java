package com.app.medibox.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.app.medibox.R;

public class PersonalActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        View btn_myMedicinalBox= findViewById(R.id.myMedicinalBox);
        btn_myMedicinalBox.setOnClickListener(this);
        View btn_takeMedicineRemind= findViewById(R.id.takeMedicineRemind);
        btn_takeMedicineRemind.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.myMedicinalBox:{
                intent = new Intent(this, MyMedicinalBoxActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.takeMedicineRemind:{
                intent = new Intent(this, TakeMedicineRemindActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
