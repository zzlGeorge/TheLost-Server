package com.george.utils.javaBeanCreater;

import com.george.utils.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 * Created by George on 2017/11/27.
 */
public class BeanProcess {

    /**
     * 从表结构中去生成javabean
     *
     * @param beanName
     * @return
     */
    public static String genJavaBeanFromTableStructure(List<ColumnModel> columnModelList, String beanName, String packagePath) {
        StringBuffer sb = new StringBuffer();
        try {
            //java package引入
            sb.append("package " + packagePath.replace("\\", ".") + ";\r\n");
            //java import引入
            for (ColumnModel columnModel : columnModelList) {
                String columnClassName = columnModel.getColumnClassName();
                if (!columnClassName.contains("lang") && !sb.toString().contains(columnClassName)) {
                    sb.append("import " + columnModel.getColumnClassName() + ";\r\n");
                }
            }
            sb.append("\r\n");
            //java 类声明
            sb.append("public class " + toFirstCharUpCase(beanName) + " {\r\n");
            //java 属性
            for (ColumnModel columnModel : columnModelList) {
                if (StringUtils.isNotBlank(columnModel.getRemarks())) {
                    sb.append("\tprivate " + columnModel.getFieldType() + " " + columnModel.getFieldName() + ";");
                    sb.append("\t//" + columnModel.getRemarks() + " \r\n");
                } else {
                    sb.append("\tprivate " + columnModel.getFieldType() + " " + columnModel.getFieldName() + ";\r\n");
                }
            }
            sb.append("\r\n");
            //java 属性get set方法
            for (ColumnModel columnModel : columnModelList) {
                sb.append(
                        "\tpublic " + columnModel.getFieldType() + " get" + toFirstCharUpCase((String) columnModel.getFieldName()) + "() {\r\n" +
                                "\t\treturn " + columnModel.getFieldName() + ";\r\n" +
                                "\t}\r\n" +
                                "\r\n" +
                                "\tpublic void set" + toFirstCharUpCase((String) columnModel.getFieldName()) + "(" + columnModel.getFieldType() + " " + columnModel.getFieldName() + ") {\r\n" +
                                "\t\t this." + columnModel.getFieldName() + " = " + columnModel.getFieldName() + ";\r\n" +
                                "\t}\r\n\r\n");
            }
            sb.append("}\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 将bean信息读到磁盘上
     *
     * @param classPath
     * @param packagePath
     * @param tableName
     * @param beanClassName
     * @param connection
     * @throws IOException
     */
    public static void createBeanInDisk(String classPath, String packagePath, String tableName, String beanClassName, Connection connection) throws IOException {
        List<ColumnModel> columnModelList = JdbcUtil.getTableStructure(tableName, connection);
        String beanDetail = genJavaBeanFromTableStructure(columnModelList, beanClassName, packagePath);
        System.out.println("已生成数据：\r" + beanDetail);
        String packagePathStr = packagePath.replace(".", "/");
        File javaFile = new File(classPath + File.separator + packagePathStr + File.separator + toFirstCharUpCase(beanClassName) + ".java");
        FileUtils.writeStringToFile(javaFile, beanDetail);
    }


    /**
     * 将首字母变大写
     *
     * @param str
     * @return
     */
    public static String toFirstCharUpCase(String str) {
        char[] columnCharArr = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < columnCharArr.length; i++) {
            char cur = columnCharArr[i];
            if (i == 0) {
                sb.append(Character.toUpperCase(cur));
            } else {
                sb.append(cur);
            }
        }
        return sb.toString();
    }

    /**
     * 将首字母变小写
     *
     * @param str
     * @return
     */
    public static String toFirstCharLowCase(String str) {
        char[] columnCharArr = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < columnCharArr.length; i++) {
            char cur = columnCharArr[i];
            if (i == 0) {
                sb.append(Character.toLowerCase(cur));
            } else {
                sb.append(cur);
            }
        }
        return sb.toString();
    }


    /**
     * @param basePath      项目根路径
     * @param packagePath   javabean包路径
     * @param connection    数据库连接
     * @param tablename     数据库表名
     * @param beanClassName javabean类名
     */
    public static boolean generateJavaBeanFromDB(String basePath, String packagePath, Connection connection,
                                                 String tablename, String beanClassName) {
        boolean success = false;
        try {
            createBeanInDisk(basePath, packagePath, tablename, beanClassName, connection);
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success;
    }
}
