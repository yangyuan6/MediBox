package com.app.medibox.UI.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.app.medibox.R;
import com.app.medibox.bean.Remind;

import java.util.ArrayList;

/**
 * Created by yangy on 2018/5/5.
 */

public class MyRemindListAdapter extends BaseAdapter implements View.OnClickListener {
    private ArrayList<Remind> reminds;
    private Context context;
    private Callback mCallback;

    public MyRemindListAdapter(Context context, ArrayList<Remind> reminds, Callback callback) {
        this.context = context;
        this.reminds = reminds;
        this.mCallback = callback;
    }

    public interface Callback {
        public void click(View v);
    }

    @Override
    public int getCount() {
        return reminds.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        Remind item = reminds.get(position);
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.remind_item, null);
            viewHolder = new ViewHolder();
            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
            viewHolder.timePart = (TextView) convertView.findViewById(R.id.timePart);
            viewHolder.medicineName = (TextView) convertView.findViewById(R.id.tv_medicine_name);
            viewHolder.medicineName1 = (TextView) convertView.findViewById(R.id.tv_medicine_name1);
            viewHolder.btn_ecit = (Button) convertView.findViewById(R.id.btn_edit);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.time.setText(item.getTime());
        viewHolder.timePart.setText(item.getTimePart());
        viewHolder.medicineName.setText(item.getMedicineName());
        viewHolder.medicineName1.setText(item.getMedicineName1());
        viewHolder.btn_ecit.setOnClickListener(this);
        viewHolder.btn_ecit.setTag(position);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        mCallback.click(v);
    }

    @Override
    public Object getItem(int position) {
        return reminds.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        public TextView timePart;
        public TextView time;
        public TextView medicineName;
        public TextView medicineName1;
        public Button btn_ecit;
    }
    public void updataView(int posi, ListView listView,Remind remind) {
        int visibleFirstPosi = listView.getFirstVisiblePosition();
        int visibleLastPosi = listView.getLastVisiblePosition();
        if (posi >= visibleFirstPosi && posi <= visibleLastPosi) {
            View view = listView.getChildAt(posi - visibleFirstPosi);
            ViewHolder holder = (ViewHolder) view.getTag();
            holder.time.setText(remind.getTime());
            holder.timePart.setText(remind.getTimePart());
            holder.medicineName.setText(remind.getMedicineName());
            holder.medicineName1.setText(remind.getMedicineName1());
            reminds.set(posi, remind);
        } else {
            reminds.set(posi, remind);
        }
    }
}

