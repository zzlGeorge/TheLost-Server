<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperClass}">

    <sql id="baseColumns">
        id, <#list columns as column> ${column} <#if column_has_next>,</#if></#list>
    </sql>

    <select id="get" parameterType="${idClass}" resultType="${pojoClass}" >
        SELECT <include refid="baseColumns"/> FROM ${table} WHERE id = ${r"#"}{id}
    </select>

    <select id="findByEntity" parameterType="${pojoClass}" resultType="${pojoClass}">
        SELECT <include refid="baseColumns"/> FROM ${table}
        <where>
            <#list columns as column>
                <if test="${column} != null">
                    <#if column_index != 0> AND</#if> ${column} = ${r"#"}{entity.${column}}
                </if>
            </#list>
        </where>
        <include refid="com.george.utils.generators.mybatis.mapper.CRUDMapper.orderBy"/>
    </select>

    <select id="criteriaSearch" resultType="${pojoClass}">
        SELECT <include refid="baseColumns"/> FROM ${table}
        <where>
            <include refid="com.george.utils.generators.mybatis.mapper.CRUDMapper.conditions"/>
        </where>
        <include refid="com.george.utils.generators.mybatis.mapper.CRUDMapper.orderBy"/>
    </select>

    <insert id="save" parameterType="${pojoClass}" useGeneratedKeys="true" keyProperty="entity.id">
        INSERT INTO ${table}(<include refid="baseColumns"/>) VALUE
        (${r"#"}{entity.id}, <#list columns as column> ${r"#"}{entity.${column}} <#if column_has_next>,</#if> </#list>)
    </insert>

    <update id="update" parameterType="${pojoClass}">
        UPDATE ${table} SET
        <#list columns as column>
            ${column} = ${r"#"}{entity.${column}} <#if column_has_next>,</#if>
        </#list>
        WHERE id = ${r"#"}{entity.id}
    </update>

    <delete id="delete" parameterType="${idClass}">
        DELETE FROM ${table} WHERE id = ${r"#"}{id}
    </delete>
</mapper>