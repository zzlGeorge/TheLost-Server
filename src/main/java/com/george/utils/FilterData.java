package com.george.utils;

import com.george.web.ParamObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by George on 2018/1/3.
 */
public class FilterData {

    /**
     * ��ҳ����
     */
    public static <T> ParamObject splitPage(ParamObject param, List<T> dataSet) {
        List<Object> res = new LinkedList<Object>();
        if (dataSet != null && dataSet.size() > 0) {
            for (int i = 0; i < dataSet.size(); i++) {
                if (i >= param.getPageSize() * param.getPageNumber() - param.getPageSize()
                        && i < param.getPageSize() * param.getPageNumber()) {
                    res.add(dataSet.get(i));
                }
            }
            param.setDataList(res);
            param.setTotalDisplaySize(dataSet.size());
        }
        return param;
    }

}

