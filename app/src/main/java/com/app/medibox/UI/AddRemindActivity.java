package com.app.medibox.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.medibox.R;
import com.app.medibox.bean.Remind;
import com.app.medibox.util.SaveRemind2SdUtil;

import java.util.ArrayList;

public class AddRemindActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView remind_time_edit;
    private EditText remind_medicine1_edit;
    private EditText remind_medicine2_edit;
    private Remind remind = new Remind();
    private boolean updateTag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_remind);
        remind_time_edit = (TextView) findViewById(R.id.remind_time_edit);
        remind_medicine1_edit = (EditText) findViewById(R.id.remind_medicine1_edit);
        remind_medicine2_edit = (EditText) findViewById(R.id.remind_medicine2_edit);
        View btn_add_remind = findViewById(R.id.btn_add_remind);
        btn_add_remind.setOnClickListener(this);
        View btn_add_remind_time = findViewById(R.id.btn_add_remind_time);
        btn_add_remind_time.setOnClickListener(this);
        Intent intent = getIntent();
        Remind remind = (Remind) intent.getSerializableExtra("remind");
        if (remind != null) {
            updateTag=true;
            remind_time_edit.setText(remind.getTime());
            remind_medicine1_edit.setText(remind.getMedicineName());
            remind_medicine2_edit.setText(remind.getMedicineName1());
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_add_remind: {
                String str_remind_time_edit = remind_time_edit.getText().toString();
                String str_remind_medicine1_edit = remind_medicine1_edit.getText().toString();
                String str_remind_medicine2_edit = remind_medicine2_edit.getText().toString();
                if (str_remind_time_edit == null || "".equals(str_remind_time_edit) ||
                        str_remind_medicine1_edit == null || "".equals(str_remind_medicine1_edit) ||
                        str_remind_medicine2_edit == null || "".equals(str_remind_medicine2_edit)
                        ) {
                    Toast.makeText(this, "请填写完每一项后再提交", Toast.LENGTH_SHORT).show();
                    break;
                } else {

                    remind.setTime(str_remind_time_edit);
                    remind.setMedicineName(str_remind_medicine1_edit);
                    remind.setMedicineName1(str_remind_medicine2_edit);
                    String hour = str_remind_time_edit.split(":")[0];
                    int intHour = Integer.parseInt(hour);
                    if (intHour < 12) {
                        remind.setTimePartTag(1);
                        remind.setTimePart("AM");
                    } else if (intHour >= 12 && intHour < 19) {
                        remind.setTimePart("PM");
                        remind.setTimePartTag(2);
                    } else {
                        remind.setTimePart("PM");
                        remind.setTimePartTag(3);
                    }
                    ArrayList<Remind> reminds = SaveRemind2SdUtil.get(remind.getTimePartTag());
                    if(updateTag){
                        for (int i=0;i<reminds.size();i++){
                            if (remind.getId().equals(reminds.get(i).getId())){
                                reminds.set(i,remind);
                                Toast.makeText(this, "更新成功！", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        }
                    }else{
                        reminds.add(remind);
                        Toast.makeText(this, "添加成功！", Toast.LENGTH_SHORT).show();
                    }
                    SaveRemind2SdUtil.save(reminds, remind.getTimePartTag());
                    Intent intent1=new Intent();
                    intent1.putExtra("remind",remind);
                    setResult(100,intent1);
                    this.finish();
                }
                break;
            }
            case R.id.btn_add_remind_time: {
                intent = new Intent(this, AlarmActivity.class);
                startActivityForResult(intent, 1);
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String time = data.getStringExtra("time");
        remind_time_edit.setText(time);
        remind.setTime(time);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String str_remind_time_edit = remind_time_edit.getText().toString();
        String str_remind_medicine1_edit = remind_medicine1_edit.getText().toString();
        String str_remind_medicine2_edit = remind_medicine2_edit.getText().toString();
        if (str_remind_time_edit == null || "".equals(str_remind_time_edit) ||
                str_remind_medicine1_edit == null || "".equals(str_remind_medicine1_edit) ||
                str_remind_medicine2_edit == null || "".equals(str_remind_medicine2_edit)
                ) {
            Toast.makeText(this, "请填写完每一项后再提交", Toast.LENGTH_SHORT).show();
        } else {

            remind.setTime(str_remind_time_edit);
            remind.setMedicineName(str_remind_medicine1_edit);
            remind.setMedicineName1(str_remind_medicine2_edit);
            String hour = str_remind_time_edit.split(":")[0];
            int intHour = Integer.parseInt(hour);
            if (intHour < 12) {
                remind.setTimePartTag(1);
                remind.setTimePart("AM");
            } else if (intHour >= 12 && intHour < 19) {
                remind.setTimePart("PM");
                remind.setTimePartTag(2);
            } else {
                remind.setTimePart("PM");
                remind.setTimePartTag(3);
            }
            ArrayList<Remind> reminds = SaveRemind2SdUtil.get(remind.getTimePartTag());
            if(updateTag){
                for (int i=0;i<reminds.size();i++){
                    if (remind.getId().equals(reminds.get(i).getId())){
                        reminds.set(i,remind);
                        Toast.makeText(this, "更新成功！", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }else{
                reminds.add(remind);
                Toast.makeText(this, "添加成功！", Toast.LENGTH_SHORT).show();
            }
            SaveRemind2SdUtil.save(reminds, remind.getTimePartTag());
            Intent intent1=new Intent();
            intent1.putExtra("remind",remind);
            setResult(100,intent1);
        }
    }
}
