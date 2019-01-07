package com.app.medibox.util;

import android.os.Environment;

import com.app.medibox.bean.Remind;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangy on 2017/3/5.
 */

public class SaveRemind2SdUtil {
    public static void save(ArrayList<Remind> reminds,int tag) {
        Gson gson = new Gson();
        String strDbname="remindsInfo1.db";
        if (tag==1){
            strDbname="remindsInfo1.db";
        }else if(tag==2){
            strDbname="remindsInfo2.db";
        }else if(tag==3){
            strDbname="remindsInfo3.db";
        }
        String filePath = Environment.getExternalStorageDirectory().getPath() + "/MediBoxDir/"+strDbname;
        try {
            FileUtil.CreateDirAndFile(filePath);
            FileOutputStream writerStream = new FileOutputStream(filePath);
            BufferedWriter oWriter = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8"));
            oWriter.write(gson.toJson(reminds));
            oWriter.flush();
            oWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Remind> get(int tag) {
        ArrayList<Remind> reminds = new ArrayList<>();
        String str = "";
        String strDbname="remindsInfo1.db";
        if (tag==1){
            strDbname="remindsInfo1.db";
        }else if(tag==2){
            strDbname="remindsInfo2.db";
        }else if(tag==3){
            strDbname="remindsInfo3.db";
        }
        String filePath = Environment.getExternalStorageDirectory().getPath() + "/MediBoxDir/"+strDbname;
        try {
            FileUtil.CreateDirAndFile(filePath);
            File file = new File(filePath);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            str = bufferedReader.readLine();
            reminds = new Gson().fromJson(str, new TypeToken<List<Remind>>() {
            }.getType());
            if (reminds==null)reminds=new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reminds;
    }
}
