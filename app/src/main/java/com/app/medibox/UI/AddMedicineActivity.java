package com.app.medibox.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.app.medibox.R;
import com.app.medibox.bean.Medicinal;
import com.app.medibox.util.SaveMedicinal2SdUtil;

import java.util.ArrayList;

public class AddMedicineActivity extends AppCompatActivity implements View.OnClickListener {

   private EditText medicinal_name_edit;
   private EditText functionalTreatment;
   private EditText taboo_edit;
   private EditText timeLimit_edit;
   private EditText placement_edit;
   private EditText id_code_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicinal);
        medicinal_name_edit=(EditText)findViewById(R.id.medicinal_name_edit);
        functionalTreatment=(EditText)findViewById(R.id.functionalTreatment_edit);
        taboo_edit=(EditText)findViewById(R.id.taboo_edit);
        timeLimit_edit=(EditText)findViewById(R.id.timeLimit_edit);
        placement_edit=(EditText)findViewById(R.id.placement_edit);
        id_code_edit=(EditText)findViewById(R.id.id_code_edit);
        View btn_add_medicine=findViewById(R.id.btn_add_medicine);
        btn_add_medicine.setOnClickListener(this);
        Intent intent=getIntent();
        Medicinal medicinal=(Medicinal) intent.getSerializableExtra("medicinal");
        if (medicinal!=null){
            medicinal_name_edit.setText(medicinal.getName());
            functionalTreatment.setText(medicinal.getFunction());
            taboo_edit.setText(medicinal.getUsageMethod());
            timeLimit_edit.setText(medicinal.getTime());
            placement_edit.setText(medicinal.getPlace());
            id_code_edit.setText(medicinal.getIdCode());
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn_add_medicine:{
                String str_medicinal_name_edit=medicinal_name_edit.getText().toString();
                String str_functionalTreatment=functionalTreatment.getText().toString();
                String str_taboo_edit=taboo_edit.getText().toString();
                String str_timeLimit_edit=timeLimit_edit.getText().toString();
                String str_placement_edit=placement_edit.getText().toString();
                String str_id_code_edit=id_code_edit.getText().toString();
                if (str_medicinal_name_edit==null||"".equals(str_medicinal_name_edit)||
                        str_functionalTreatment==null||"".equals(str_functionalTreatment)||
                        str_taboo_edit==null||"".equals(str_taboo_edit)||
                        str_timeLimit_edit==null||"".equals(str_timeLimit_edit)||
                        str_placement_edit==null||"".equals(str_placement_edit)||
                        str_id_code_edit==null||"".equals(str_id_code_edit)
                        ){
                    Toast.makeText(this,"请填写完每一项后再提交",Toast.LENGTH_LONG).show();
                    break;
                }else{
                    Medicinal medicinal=new Medicinal();
                    medicinal.setName(str_medicinal_name_edit);
                    medicinal.setFunction(str_functionalTreatment);
                    medicinal.setUsageMethod(str_taboo_edit);
                    medicinal.setTime(str_timeLimit_edit);
                    medicinal.setPlace(str_placement_edit);
                    medicinal.setIdCode(str_id_code_edit);
                    ArrayList<Medicinal> medicinals= SaveMedicinal2SdUtil.get();
                    medicinals.add(medicinal);
                    SaveMedicinal2SdUtil.save(medicinals);
                    Toast.makeText(this,"添加成功！",Toast.LENGTH_SHORT).show();
                    this.finish();
                }
                break;
            }
        }
    }
}
