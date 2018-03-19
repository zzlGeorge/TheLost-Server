package com.george.utils.jdbcUtils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class JdbcUtil {
    // 表示定义数据库的用户名
    private String username;

    // 定义数据库的密码
    private String password;

    // 定义数据库的驱动信息
    private String driver;

    // 定义访问数据库的地址
    private String url;

    /**
     * 加载数据库配置信息，并给相关的属性赋值
     *
     * @param propPath 配置文件路径
     * @param propKeys 配置文件k-v值
     */
    private void loadConfig(String propPath, Map<String, String> propKeys) {
        try {
            InputStream inStream = JdbcUtil.class
                    .getResourceAsStream(propPath);
            Properties prop = new Properties();
            prop.load(inStream);
            username = prop.getProperty(propKeys.get("username"));
            password = prop.getProperty(propKeys.get("password"));
            driver = prop.getProperty(propKeys.get("driver"));
            url = prop.getProperty(propKeys.get("url"));
        } catch (Exception e) {
            throw new RuntimeException("读取数据库配置文件异常！", e);
        }
    }

    public JdbcUtil() {
        Map<String, String> propkeys = new HashMap<String, String>();
        propkeys.put("username", "jdbc.username");
        propkeys.put("password", "jdbc.password");
        propkeys.put("driver", "jdbc.driver");
        propkeys.put("url", "jdbc.url");
        loadConfig("/jdbc.properties", propkeys);
    }

    public JdbcUtil(String propPath, Map<String, String> propKeys) {
        loadConfig(propPath, propKeys);
    }

    /**
     * 获取数据库连接
     *
     * @return 数据库连接
     */
    public Connection getConnection() {
        Connection connection;
        try {
            Class.forName(driver); // 注册驱动
            connection = DriverManager.getConnection(url, username, password); // 获取连接
        } catch (Exception e) {
            throw new RuntimeException("get connection error!", e);
        }
        return connection;
    }
}
