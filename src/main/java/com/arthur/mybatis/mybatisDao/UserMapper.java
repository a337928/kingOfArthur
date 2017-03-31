package com.arthur.mybatis.mybatisDao;

import com.arthur.bean.TestBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by wangtao on 17/3/29.
 */
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USER WHERE NAME = #{name}")
    TestBean findByName(@Param("name") String name);

    @Insert("INSERT INTO TEST(ID, NAME) VALUES(#{id}, #{name})")
    void insert(@Param("id") Long id, @Param("name") String name);

}
