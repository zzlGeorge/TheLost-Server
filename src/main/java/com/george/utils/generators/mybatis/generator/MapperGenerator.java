package com.george.utils.generators.mybatis.generator;

import com.george.utils.ReflectUtils;
import com.george.utils.generators.mybatis.annotation.MyBatisMapper;
import com.george.utils.generators.mybatis.generator.annotation.ID;
import com.george.utils.generators.mybatis.generator.annotation.Ignore;
import com.george.utils.generators.mybatis.generator.annotation.Table;
import com.george.utils.generators.mybatis.mapper.CRUDMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by xiam on 2015/6/19.
 */
public class MapperGenerator {

    private static final Class<CRUDMapper> CRUD_MAPPER_CLASS = CRUDMapper.class;

    private static final String CURD_MAPPER_PACKAGE = CRUD_MAPPER_CLASS.getPackage().getName();

    private static final Class<MyBatisMapper> MAPPER_SCANNER_CLASS = MyBatisMapper.class;

    private static final String MAPPER_SCANNER_PACKAGE = MAPPER_SCANNER_CLASS.getPackage()
            .getName();

    private static final String MAPPER_SCANNER = MAPPER_SCANNER_CLASS.getSimpleName();

    private static final String INTERFACE_TPL = "mapperInterface.ftl";

    private static final String MAPPER_TPL = "mapperXml.back.ftl";

    private static final String DEFAULT_ID_COLUMN = "id";

    private Configuration freeMarkerConfiguration;

    public void setFreeMarkerConfiguration(Configuration freeMarkerConfiguration) {
        this.freeMarkerConfiguration = freeMarkerConfiguration;
    }

    public void generateMapper(Class<?> pojoClass, String mapperClass, String srcTarget,
                               boolean replace) {
        String mapperPath = srcTarget + File.separator + getMapperName(mapperClass) + ".xml";
        File mapperFile = new File(mapperPath);
        if (!replace && mapperFile.exists()) {
            return;
        }
        mkdirs(mapperFile);
        Collection<Field> allFields = ReflectUtils.getAllFields(pojoClass);
        List<String> columns = new ArrayList<String>();
        Field idField = getIdField(allFields);
        for (Field field : allFields) {
            if (!field.equals(idField) && field.getAnnotation(Ignore.class) == null) {
                columns.add(field.getName());
            }
        }
        Map<String, Object> ctx = new HashMap<String, Object>();
        ctx.put("columns", columns);
        ctx.put("idClass", Long.class.getName());
        Table table = pojoClass.getAnnotation(Table.class);
        String tableName = null;
        if (table == null || table.name().equals(StringUtils.EMPTY)) {
            tableName = pojoClass.getSimpleName();
        } else {
            tableName = table.name();
        }
        ctx.put("table", tableName);
        ctx.put("mapperClass", mapperClass);
        ctx.put("pojoClass", pojoClass.getName());
        try {
            Template template = freeMarkerConfiguration.getTemplate(MAPPER_TPL);
            String java = FreeMarkerTemplateUtils.processTemplateIntoString(template, ctx);
            FileUtils.writeStringToFile(mapperFile, java);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String generateMapperInterface(Class<?> pojoClass, String packageName,
                                          String interfaceName, String srcTarget, boolean replace) {

        String pojoPackage = pojoClass.getPackage().getName();
        if (packageName == null) {
            packageName = getParentPackage(pojoPackage) + ".mapper";
        }
        if (interfaceName == null) {
            interfaceName = pojoClass.getSimpleName() + "Mapper";
        }
        String interfaceClass = packageName + "." + interfaceName;
        String javaPath = srcTarget + "/"
                + StringUtils.replaceChars(interfaceClass, '.', File.separatorChar) + ".java";
        File javaFile = new File(javaPath);
        if (!replace && javaFile.exists()) {
            return null;
        }
        mkdirs(javaFile);
        Map<String, Object> ctx = new HashMap<String, Object>();
        ctx.put("crudMapperClass", CRUD_MAPPER_CLASS.getName());
        ctx.put("crudMapperPackage", CURD_MAPPER_PACKAGE);
        ctx.put("pojoClass", pojoClass.getName());
        ctx.put("packageName", packageName);
        ctx.put("pojoPackage", pojoPackage);
        ctx.put("pojoName", pojoClass.getSimpleName());
        ctx.put("idName", Long.class.getSimpleName());
        ctx.put("interfaceName", interfaceName);
        ctx.put("mapperScannerClass", MAPPER_SCANNER_CLASS.getName());
        ctx.put("mapperScannerPackage", MAPPER_SCANNER_PACKAGE);
        ctx.put("mapperScannerName", MAPPER_SCANNER);
        try {
            Template template = freeMarkerConfiguration.getTemplate(INTERFACE_TPL);
            String java = FreeMarkerTemplateUtils.processTemplateIntoString(template, ctx);
            FileUtils.writeStringToFile(javaFile, java);
            return interfaceClass;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static final void mkdirs(File file) {
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
    }

    private static String getParentPackage(String packageName) {
        int idx = packageName.lastIndexOf(".");
        if (idx > -1) {
            return packageName.substring(0, idx);
        }
        return null;
    }

    private static String getMapperName(String mapperClass) {
        int idx = mapperClass.lastIndexOf(".");
        if (idx > -1) {
            return mapperClass.substring(idx + 1, mapperClass.length());
        }
        return null;
    }

    private Field getIdField(Collection<Field> fields) {
        for (Field field : fields) {
            ID anno = field.getAnnotation(ID.class);
            if (anno != null) {
                return field;
            }
        }

        for (Field field : fields) {
            if (field.getName().equals(DEFAULT_ID_COLUMN)) {
                return field;
            }
        }

        throw new RuntimeException("Identity field not found.");
    }
}
