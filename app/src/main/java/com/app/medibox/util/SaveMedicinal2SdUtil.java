package com.app.medibox.util;

import android.os.Environment;

import com.app.medibox.bean.Medicinal;
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

public class SaveMedicinal2SdUtil {
    public static void save(ArrayList<Medicinal> medicinals) {
        Gson gson = new Gson();
        String filePath = Environment.getExternalStorageDirectory().getPath() + "/MediBoxDir/medicinalsInfo.db";
        try {
            FileUtil.CreateDirAndFile(filePath);
            FileOutputStream writerStream = new FileOutputStream(filePath);
            BufferedWriter oWriter = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8"));
            oWriter.write(gson.toJson(medicinals));
            oWriter.flush();
            oWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Medicinal> get() {
        ArrayList<Medicinal> medicinals = new ArrayList<>();
        String str = "";
        String filePath = Environment.getExternalStorageDirectory().getPath() + "/MediBoxDir/medicinalsInfo.db";
        try {
            FileUtil.CreateDirAndFile(filePath);
            File file = new File(filePath);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            str = bufferedReader.readLine();
            medicinals = new Gson().fromJson(str, new TypeToken<List<Medicinal>>() {
            }.getType());
            if (medicinals==null)medicinals=new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return medicinals;
    }
}
