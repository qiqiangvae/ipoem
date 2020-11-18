package org.qiqiang.chinesepoetry.utils;

import org.springframework.beans.BeanUtils;

/**
 * @author qiqiang
 * @date 2020-11-18 11:38 上午
 */
public class PojoUtils {
    public static <T, S> void copyProperties(T target, S source) {
        BeanUtils.copyProperties(source, target);
    }

    public static <T, S> void copyProperties(T target, S source, PostProcessor postProcessor) {
        BeanUtils.copyProperties(source, target);
        if (postProcessor != null) {
            postProcessor.postProcess();
        }
    }

    @FunctionalInterface
    public interface PostProcessor {
        /**
         * 后置处理
         */
        void postProcess();
    }
}