package com.jhg.marketing.common;

import java.lang.reflect.Field;
import java.util.*;

import javax.persistence.Id;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.util.StringUtil;

public class DataColumnUtils {
    public static List<Field> getAllFields(Class modelType) {
        List<Field> fields = new ArrayList<Field>();
        List<Class> clzs = new ArrayList<Class>();
        Class tmpClz = modelType;
        do {
            clzs.add(tmpClz);
            tmpClz = tmpClz.getSuperclass();
        } while (tmpClz != null && tmpClz != Object.class);
        Collections.reverse(clzs);

        for (Class clz : clzs) {
            fields.addAll(Arrays.asList(clz.getDeclaredFields()));
        }
        return fields;

    }

    /**
     * ???model?????? datacolumn
     *
     * @param modelType
     * @return
     */
    public static List<DataColumn> getColumnsByClzz(Class modelType) {

        List<DataColumn> columns = new ArrayList<DataColumn>();
        Class tmpClz = modelType;
        List<Field> fields = getAllFields(modelType);


        for (Field field : fields) {

            DataColumn column = new DataColumn();
            //???????????
            column.setColumnName(field.getName());
            //???????
            column.setDataType(field.getType());
            column.setStrDataType(field.getType().getSimpleName().toLowerCase());
            //???????
            String display = column.getColumnName();
            Explain explain = field.getAnnotation(Explain.class);
            if (explain != null && !explain.isShow()) {
                column.setShow(explain.isShow());
            } else {
                column.setShow(true);
            }
            if (explain != null) {
                display = explain.value();
            }
            column.setDisplay(display);
            //?????????
            int order = fields.indexOf(field) + 1;
            if (explain != null && explain.order() != 0) {
                column.setOrder(explain.order());
            } else {
                column.setOrder(order);
            }

            //?????????
            if (explain != null) {
                column.setControlType(explain.dataType());
            }

            //??????
            if (explain != null) {
                column.setReadOnly(!explain.isEdit());
            }

            if (explain != null) {
                if ("".equals(explain.dataTokens())) {
                    column.setDataTokens(null);
                } else {
                    column.setDataTokens(JSON.parseObject(explain.dataTokens(), Map.class));
                }
            }
            //?????????????

            if (StringUtil.isEmpty(column.getControlType())) {
                if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                    column.setControlType("switch");
                }
                if (field.getType() == Date.class) {
                    column.setControlType("date");
                }
            }

            Id id = field.getAnnotation(Id.class);
            if (id != null) {
                column.setPrimaryKey(true);
                column.setRequired(true);
            } else if (explain != null) {
                column.setRequired(explain.required());
            }
            columns.add(column);
        }
        columns.sort((a, b) -> a.getOrder() - b.getOrder());
        return columns;
    }

}
