package com.app.medibox.util;

import java.util.UUID;

/**
 * Created by yangy on 2018/5/5.
 */

public class UUIDTool {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }



}

