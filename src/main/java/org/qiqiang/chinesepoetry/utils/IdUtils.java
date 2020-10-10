package org.qiqiang.chinesepoetry.utils;

import java.util.UUID;

/**
 * @author qiqiang
 * @date 2019-07-10
 */
public class IdUtils {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
