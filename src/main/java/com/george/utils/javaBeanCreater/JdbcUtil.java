package com.george.utils.javaBeanCreater;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by George on 2017/11/27.
 */
public class JdbcUtil {
    /**
     * 获取表结构
     *
     * @param tableName
     * @return
     */
    public static List<ColumnModel> getTableStructure(String tableName, Connection connection) {
        List<ColumnModel> columnModelList = new ArrayList<ColumnModel>();
        try {
            //TODO 表相关
            //ResultSet tableSet = metaData.getTables(null, "%",tableName,new String[]{"TABLE"});
            //TODO 字段相关
            ResultSet columnSet = connection.getMetaData().getColumns(null, "%", tableName, "%");
            ColumnModel columnModel = null;
            while (columnSet.next()) {
                columnModel = new ColumnModel();
                columnModel.setColumnName(columnSet.getString("COLUMN_NAME"));
                columnModel.setColumnSize(columnSet.getInt("COLUMN_SIZE"));
                columnModel.setDataType(columnSet.getString("DATA_TYPE"));
                columnModel.setRemarks(columnSet.getString("REMARKS"));
                String typeName = columnSet.getString("TYPE_NAME").toLowerCase();
                columnModel.setTypeName(typeName.contains("identity") ?
                        typeName.substring(0, typeName.indexOf(" identity")) : typeName);
                String columnClassName = ColumnTypeEnum.getColumnTypeEnumByDBType(columnModel.getTypeName());
//                String fieldName = getFieldName(columnModel.getColumnName());
                String fieldName = columnModel.getColumnName();
                String fieldType = Class.forName(columnClassName).getSimpleName();
                columnModel.setFieldName(fieldName);
                columnModel.setColumnClassName(columnClassName);
                columnModel.setFieldType(fieldType);
                columnModelList.add(columnModel);
                //System.out.println(columnModel.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return columnModelList;
    }

    /**
     * 将数据库字段转换成bean属性
     *
     * @param columnName
     * @return
     */
    private static String getFieldName(String columnName) {
        char[] columnCharArr = columnName.toLowerCase().toCharArray();
        StringBuffer sb = new StringBuffer();
        int ad = -1;
        for (int i = 0; i < columnCharArr.length; i++) {
            char cur = columnCharArr[i];
            if (cur == '_') {
                ad = i;
            } else {
                if ((ad + 1) == i && ad != -1) {
                    sb.append(Character.toUpperCase(cur));
                } else {
                    sb.append(cur);
                }
                ad = -1;
            }
        }
        return sb.toString();
    }
}
