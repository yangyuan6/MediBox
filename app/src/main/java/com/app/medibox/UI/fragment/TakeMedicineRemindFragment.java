package com.app.medibox.UI.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.app.medibox.R;
import com.app.medibox.UI.AddRemindActivity;
import com.app.medibox.UI.adapter.MyRemindListAdapter;
import com.app.medibox.bean.Remind;
import com.app.medibox.util.SaveRemind2SdUtil;

import java.util.ArrayList;

/**
 * Created by yangy on 2018/5/3.
 */

public class TakeMedicineRemindFragment extends Fragment {
    private TextView tv;
    private int tag;
    private ArrayList<Remind> reminds;
    private MyRemindListAdapter myRemindListAdapter;
    private int position;
    private  ListView listView;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_show_remind1, container, false);
        Bundle args = getArguments();
        if (args != null) {
            tag = args.getInt("tag",1);
        }
        reminds = SaveRemind2SdUtil.get(tag);
        /*for (int i = 0; i < 5; i++) {
            Remind remind = new Remind();
            remind.setTime("10:40");
            remind.setTimePart("PM");
            remind.setMedicineName("治疗头疼药 x1");
            remind.setMedicineName1("成长快乐 x2");

            reminds.add(remind);
        }*/listView = (ListView) view.findViewById(R.id.lv_remind_list);
        MyRemindListAdapter.Callback callback=new MyRemindListAdapter.Callback() {
            @Override
            public void click(View v) {
                position=(Integer)v.getTag();
                Intent intent=new Intent(getActivity(), AddRemindActivity.class);
                intent.putExtra("remind",reminds.get(position));
                startActivityForResult(intent,1);

            }
        };
        myRemindListAdapter=new MyRemindListAdapter(getContext(),reminds,callback );
        listView.setAdapter(myRemindListAdapter);
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode==100){
            Remind remind=(Remind)data.getSerializableExtra("remind");
            myRemindListAdapter.updataView(position,listView,remind);
        }
    }

}
