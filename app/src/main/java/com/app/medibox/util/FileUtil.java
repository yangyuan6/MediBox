package com.app.medibox.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.io.File;
import java.io.IOException;

/**
 * Created by yangy on 2016/11/30.
 */
public class FileUtil {
    //创建文件夹及文件
    public static void CreateDirAndFile(String filePath) throws IOException {
        //String baseDir= Environment.getExternalStorageDirectory().getPath()+"/WifrSharingSaveDir/";
        String str_dir=filePath.substring(0,filePath.lastIndexOf("/"));
        File dir = new File(str_dir);
        if (!dir.exists()) {
            try {
                //按照指定的路径创建文件夹
                dir.mkdirs();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                //在指定的文件夹中创建文件
                file.createNewFile();
            } catch (Exception e) {
            }
        }

    }

    public static String getPath(Context context, Uri uri) {

        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = { "_data" };
            Cursor cursor = null;

            try {
                cursor = context.getContentResolver().query(uri, projection,null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                // Eat it
            }
        }

        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }
    public static String getType(String filePath){
        return filePath.substring(filePath.lastIndexOf('.')+1,filePath.length());
    }
}
