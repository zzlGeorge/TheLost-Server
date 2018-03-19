package com.george.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by George on 2018/1/3.
 */
public class CommonUtils {
    /**
     * �ж��ַ����Ƿ�Ϊ�ջ�մ�
     */
    public static boolean strIsEmpty(String str) {
        if (str == null || (str != null && str.equals("")))
            return true;
        return false;
    }

    /**
     * �ж�time�Ƿ���from��to֮��
     *
     * @param time ָ������
     * @param from ��ʼ����
     * @param to   ��������
     * @return
     */
    public static boolean belongCalendar(Date time, Date from, Date to) {
        Calendar date = Calendar.getInstance();
        date.setTime(time);

        Calendar after = Calendar.getInstance();
        after.setTime(from);

        Calendar before = Calendar.getInstance();
        before.setTime(to);

        if (date.after(after) && date.before(before)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ���ַ�����ʽ����ת��Ϊָ����ʽ��������
     */
    public static Date stringToDate(String date, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);//yyyy-MM-dd
        try {
            Date date1 = df.parse(date);
            System.out.println();
            return date1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
