package com.george.utils.generators.mybatis.mapper;

import com.george.utils.PageParam;
import com.george.utils.Pagine;
import com.george.utils.generators.mybatis.query.Condition;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiam on 2015/6/19.
 */
public interface CRUDMapper<T, ID extends Serializable> {

    T get(@Param("id") ID id);

    List<T> findByEntity(@Param("entity") T entity, @Param("page") Pagine<T> pagine);

    List<T> findByEntityAll(@Param("entity") T entity);

    //xiami
    List<T> criteriaSearch(@Param("conditions") List<Condition<Object>> conditions,
                           @Param("page") Pagine<T> pagine);

    //hxh
    List<T> search(@Param("entity") T entity, @Param("pageParam") PageParam pageParam);

    int count(@Param("entity") T entity);

    int save(@Param("entity") T entity);

    int update(@Param("entity") T entity);

    int delete(@Param("id") ID id);

    /**
     * 自定义where条件
     *
     * @param whereCondition
     * @return
     */
    List<T> customCondition(@Param("whereCondition") String whereCondition);
}
