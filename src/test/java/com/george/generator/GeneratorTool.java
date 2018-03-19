package com.george.generator;

import com.george.general.Constants;
import com.george.utils.generators.mybatis.generator.MapperGenerator;
import com.george.utils.javaBeanCreater.BeanProcess;
import com.george.utils.jdbcUtils.JdbcUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiam on 2015/6/23.
 *
 * @ContextConfiguration(locations = { "classpath*:/spring1.xml", "classpath*:/spring2.xml" })
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:freemarker.xml")
public class GeneratorTool {

    @Autowired
    private MapperGenerator mapperGenerator;

    /**
     * 生成mapper的java接口和xml文件
     */
    @Test
    public void generateMapperTools() {
        //配置
//        Class<?> entityClass = TestAA.class;
        String mapperInterfacePath = "com.supplier.dao";
        String mapperXmlPath = "\\src\\main\\resources\\com\\supplier\\dao";

//        GeneratorMethods.generateMapper(mapperGenerator, entityClass, mapperInterfacePath, mapperXmlPath);
        System.out.println("生成完毕。");
    }

    /**
     * javabean生成器
     */
    @Test
    public void generateJavaBeanFromDB() {
        //配置信息
        int id = 1;//
        String basePath = Constants.ROOT_PATH + "/src/main/java";
        String packagePath = "com.supplier.entity";
        String tableName = "TestAA";
        String beanClassName = "TestAA";

        //获取connection
        Map<String, String> propkeys = new HashMap<String, String>();
        propkeys.put("username", "instrument.user");
        propkeys.put("password", "instrument.password");
        propkeys.put("driver", "instrument.driver");
        propkeys.put("url", "instrument.url");
        JdbcUtil jdbcUtil = new JdbcUtil("/props/instruments.properties", propkeys);
        Connection connection = jdbcUtil.getConnection();

        try {
            BeanProcess.generateJavaBeanFromDB(basePath, packagePath,
                    connection, tableName, beanClassName);//生成javabean文件于指定位置
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}