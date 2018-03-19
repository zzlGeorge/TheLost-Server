package com.george.utils.generators.freemarker;

import java.util.HashMap;
import java.util.Map;


/**
 * freemarker测试
 */
public class FreemarkerDemo {

    private static final String TEMPLATE_PATH = "src/main/resources/templet/freemarker";
    private static final String CLASS_PATH = "src/main/java/com/george/";

    public static void main(String[] args) {
        /*// step1 创建freeMarker配置实例
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            // step3 创建数据模型
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("classPath", "com.george");
            dataMap.put("className", "AutoCodeDemo");
            dataMap.put("helloWorld", "通过简单的 <代码自动生产程序> 演示 FreeMarker的HelloWorld！");
            // step4 加载模版文件
            Template template = configuration.getTemplate("hello.ftl");
            // step5 生成数据
            File docFile = new File(CLASS_PATH + "/" + "AutoCodeDemo.java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            // step6 输出文件
            template.process(dataMap, out);
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^AutoCodeDemo.java 文件创建成功 !");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }*/

        FreemarkerUtil freemarkerUtil = FreemarkerUtil.getInstance(TEMPLATE_PATH);
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("classPath", "com.george");
        model.put("className", "AutoCodeDemo");
        model.put("helloWorld", "通过简单的 <代码自动生产程序> 演示 FreeMarker的HelloWorld！");
//        freemarkerUtil.getContent(model, "hello.ftl");
        freemarkerUtil.create(model, "hello.ftl", CLASS_PATH + "/AutoCodeDemo.java");
    }

}