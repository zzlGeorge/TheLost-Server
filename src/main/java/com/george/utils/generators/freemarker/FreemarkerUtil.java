package com.george.utils.generators.freemarker;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Created by George on 2017/12/28.
 * <p>
 * FreeMarkerUtil ������
 */
public class FreemarkerUtil {

    // FreeMarker ����
    private static Configuration cfg;

    /**
     * ��̬�ڲ��࣬����freeMarker ���ã�ʵ����������
     */
    private static class LazyHolder {
        // ���� freeMarker ����
        private static final Configuration config = new Configuration();
        // ʵ����������
        private static final FreemarkerUtil fk = new FreemarkerUtil();
    }

    /**
     * ˽�й��캯��
     */
    private FreemarkerUtil() {

    }

    /**
     * ��ʼ�������ļ�����ȡʵ��
     *
     * @param templatePath ģ��·��
     * @return FreeMarkerUtil ������
     */
    public static FreemarkerUtil getInstance(String templatePath) {
        if (null == cfg) {
            // ���� freeMarker ����
            cfg = LazyHolder.config;
            // ���ñ����ʽ
            cfg.setDefaultEncoding("UTF-8");
            // templatePath ָ����ģ������·��
//            cfg.setClassForTemplateLoading(FreemarkerUtil.class, templatePath);
            try {
                cfg.setDirectoryForTemplateLoading(new File(templatePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return LazyHolder.fk;
    }

    /**
     * ����ģ�����Ƽ��ض�Ӧģ��
     *
     * @param templateName ģ������
     * @return
     */
    private Template getTemplate(String templateName) {
        try {
            return cfg.getTemplate(templateName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ����̨��ӡͨ��ģ�����ɵ��ļ�
     *
     * @param dataModel    ����ģ��
     * @param templateName ���ģ��
     */
    public String getContent(Map<String, Object> dataModel, String templateName) {
        try {
            StringWriter stringWriter = new StringWriter();
            getTemplate(templateName).process(dataModel, stringWriter);
            stringWriter.flush();
            String result = stringWriter.toString();
            stringWriter.close();
            return result;
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ����ͨ��ģ�����ɵ��ļ�
     *
     * @param dataModel    ����ģ��
     * @param templateName ���ģ��
     * @param filePathAndFileName     ����ļ�·��+�ļ���
     */
    public File create(Map<String, Object> dataModel, String templateName, String filePathAndFileName) {
        try {
            File docFile = new File(filePathAndFileName);
            if (!docFile.getParentFile().exists()) {
                docFile.getParentFile().mkdirs();
            }
//            BufferedWriter bufWrite = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"));
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            getTemplate(templateName).process(dataModel, out);
            out.flush();
            out.close();
            return docFile;
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ���ݵ�ַ������ݵ��ֽ���
     *
     * @param strUrl �������ӵ�ַ
     * @return ͼƬBase64��
     */
    public String getImgBase64ByUrl(String strUrl) {
        try {
            // ���� Http ����
            HttpURLConnection conn = (HttpURLConnection) new URL(strUrl).openConnection();
            // 5����Ӧ��ʱ
            conn.setConnectTimeout(5 * 1000);
            conn.setDoInput(true);
            // �ж�http�����Ƿ�������Ӧ�������ݣ����������ȡͼƬ Base64 ��
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // ��ȡͼƬ������
                InputStream inStream = conn.getInputStream();
                // ���ڴ洢ͼƬ�����
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                // ���建���������ڴ洢ͼƬ�����
                byte[] buffer = new byte[1024];
                int len = 0;
                // ͼƬ�����ѭ��д��
                while ((len = inStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
                // ͼƬ�����ת�ֽ���
                byte[] btImg = outStream.toByteArray();
                inStream.close();
                outStream.flush();
                outStream.close();
                return Base64.encode(btImg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
