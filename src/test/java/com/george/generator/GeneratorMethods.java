package com.george.generator;

import com.george.general.Constants;
import com.george.utils.generators.mybatis.generator.MapperGenerator;

/**
 * Created by George on 2017/8/29.
 */

public class GeneratorMethods {

    public static void generateMapper(MapperGenerator mapperGenerator, Class<?> clazz, String mapperInterfacePath, String mapperXmlPath) {
        String ROOT_PATH = Constants.ROOT_PATH;
        String mapperInterface = mapperGenerator.generateMapperInterface(clazz, /*"com.healthcloud.mapper.sims"*/mapperInterfacePath, null,
                ROOT_PATH + "\\src\\main\\java", true);

        mapperGenerator
                .generateMapper(
                        clazz,
                        mapperInterface,
                        ROOT_PATH + /*"\\src\\main\\resources\\mybatis\\mapper\\sims"*/mapperXmlPath,
                        true);
    }

}
