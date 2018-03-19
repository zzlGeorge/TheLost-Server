package com.george;

import com.alibaba.druid.pool.DruidDataSource;
import com.george.general.Constants;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by George on 2017/12/12.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:spring-config/druid.xml")
public class CommonTest {

//    @Autowired
//    private DruidDataSource druidDataSource;

    @Test
    public void testCommon() {
        System.out.println(Constants.ROOT_PATH);
    }


    /**
     * Druid���ӳز���
     */
    @Test
    public void testDruidPools() {
        //������һ��ʵ��
        DruidDataSource dataSource = new DruidDataSource();
        //�������ݿ����ӵ�ַ
        dataSource.setUrl("jdbc:sqlserver://192.168.20.164:1433;databaseName=MedicalSIMS001");
        dataSource.setUsername("sa");
        dataSource.setPassword("MK2017!");
        //���ó�ʼ����С
        dataSource.setInitialSize(1);
        //���������ݿ����Ӵ��е���С������
        dataSource.setMinIdle(1);
        //�������������
        dataSource.setMaxActive(20);
        //���û�ȡ���ӵ����ȴ�ʱ��
        dataSource.setMaxWait(60000);

        dataSource.setTimeBetweenEvictionRunsMillis(60000);

        dataSource.setMinEvictableIdleTimeMillis(300000);

        dataSource.setValidationQuery("SELECT 'X'");

        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setTestWhileIdle(true);

        dataSource.setPoolPreparedStatements(false);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);


        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("select 1");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getLong(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testCommon2() {
        String str = "������";
        System.out.println(str.substring(0, 2));
        System.out.println(str);
    }

}
