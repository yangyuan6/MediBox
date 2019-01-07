package com.app.medibox.UI.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.app.medibox.R;
import com.app.medibox.bean.Medicinal;
import com.app.medibox.util.SaveMedicinal2SdUtil;

import java.util.ArrayList;

/**
 * Created by yangy on 2018/5/3.
 */

public class MyFragment extends Fragment {
    private TextView tv;
    private String name;
    private ArrayList<Medicinal> medicinals;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_show_medicine1,container,false);
        Bundle args = getArguments();
        if (args != null) {
            name = args.getString("name");
        }
        medicinals= SaveMedicinal2SdUtil.get();
       /* medicinals=new ArrayList<>();
        for (int i=0;i<5;i++){
            Medicinal medicinal=new Medicinal();
            medicinal.setName("京都念慈庵枇杷膏");
            medicinal.setTime("有效期 2018.02");
            medicinal.setPlace("放置处 书房药箱");
            medicinals.add(medicinal);
        }*/
        ListView listView=(ListView)view.findViewById(R.id.lv_medicine_list);
        listView.setAdapter(new MyAdapter());

        return view;
    }
    private class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return medicinals.size();
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            Medicinal item = medicinals.get(position);
            if (convertView == null) {
                convertView = View.inflate(getActivity(), R.layout.medicine_item, null);
                viewHolder = new ViewHolder();
                viewHolder.medicinal_name = (TextView) convertView.findViewById(R.id.medicinal_name);
                viewHolder.medicinal_time = (TextView) convertView.findViewById(R.id.medicinal_time);
                viewHolder.medicinal_place = (TextView) convertView.findViewById(R.id.medicinal_place);
                convertView.setTag(viewHolder);
            } else {
                viewHolder =  (ViewHolder)convertView.getTag();
            }
            viewHolder.medicinal_name.setText(item.getName());
            viewHolder.medicinal_time.setText("有效期 "+item.getTime());
            viewHolder.medicinal_place.setText("放置处 "+item.getPlace());
            return convertView;
        }
        @Override
        public Object getItem(int position) {
            return null;
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
    }
    class ViewHolder{
        public TextView medicinal_name;
        public TextView medicinal_time;
        public TextView medicinal_place;
    }
}
