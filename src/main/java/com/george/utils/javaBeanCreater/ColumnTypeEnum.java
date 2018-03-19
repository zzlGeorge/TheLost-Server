package com.george.utils.javaBeanCreater;

/**
 * Created by George on 2017/11/27.
 */
public enum ColumnTypeEnum {
    //mysql
    VARCHAR("varchar", "java.lang.String"),
    NUMBER("number", "java.lang.Integer"),
    BLOB("blob", "java.lang.byte[]"),
    TEXT("text", "java.lang.String"),
    INTEGER("integer", "java.lang.Long"),
    TINYINT("tinyint", "java.lang.Integer"),
    SMALLINT("smallint", "java.lang.Integer"),
    MEDIUMINT("mediumint", "java.lang.Integer"),
    BIT("bit", "java.lang.Boolean"),
    FLOAT("float", "java.lang.Float"),
    DOUBLE("double", "java.lang.Double"),
    DECIMAL("decimal", "java.math.BigDecimal"),  // --
    BOOLEAN("boolean", "java.lang.Integer"),
    ID("id", "java.lang.Long"),
    DATE("date", "java.util.Date"),
    TIME("time", "java.sql.Time"),
    //    DATETIME("datetime","java.sql.Timestamp"),
    TIMESTAMP("timestamp", "java.sql.Timestamp"),
    YEAR("year", "java.util.Date"),
    CHAR("char", "java.lang.String"),
    INT("int", "java.lang.Integer"),
    BIGINT("bigint", "java.math.BigInteger"),//    --
    BIGINT_UNSIGNED("bigint unsigned","java.lang.String"),

    //sql server
    DATETIME("datetime", "java.util.Date"),
    NCHAR("nchar", "java.lang.String"),
    NVARCHAR("nvarchar", "java.lang.String"),
    NTEXT("ntext", "java.lang.String"),
    INT_IDENTITY("int identity", "java.lang.Integer"),
    BINARY("binary", "java.lang.byte[]"),
    VARBINARY("varbinary", "java.lang.byte[]"),
    IMAGE("image", "java.lang.byte[]"),

    //oracle
    VARCHAR2("varchar2", "java.lang.String"),
    RAW("row","java.lang.byte[]"),

    ;

    private String dbType;
    private String javaType;

    ColumnTypeEnum(String dbType, String javaType) {
        this.dbType = dbType;
        this.javaType = javaType;
    }

    public static String getColumnTypeEnumByDBType(String dbType) {
        for (ColumnTypeEnum columnTypeEnum : ColumnTypeEnum.values()) {
            if (columnTypeEnum.getDbType().equals(dbType)) {
                return columnTypeEnum.getJavaType();
            }
        }
        return "";
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }
}
