package com.george;

import com.alibaba.druid.pool.DruidDataSource;
import com.george.dao.entity.Player;
import com.george.general.Constants;
import com.george.utils.CommonUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

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
     * Druid连接池测试
     */
    @Test
    public void testDruidPools() {
        //创建了一个实例
        DruidDataSource dataSource = new DruidDataSource();
        //设置数据库连接地址
        dataSource.setUrl("jdbc:sqlserver://192.168.20.164:1433;databaseName=MedicalSIMS001");
        dataSource.setUsername("sa");
        dataSource.setPassword("MK2017!");
        //设置初始化大小
        dataSource.setInitialSize(1);
        //设置在数据库连接词中的最小连接数
        dataSource.setMinIdle(1);
        //设置最大连接数
        dataSource.setMaxActive(20);
        //设置获取连接的最大等待时间
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
//        Player player = new Player();
//        player.setArea("涩阿甘");
//        player.setPassword("236234");
//        player.setCreateTime(new Date());
//        Map<String, Object> keyAndValue = CommonUtils.getKeyAndValue(player);
        //%EF%BF%BD%EF%BF%BD
    }

}
