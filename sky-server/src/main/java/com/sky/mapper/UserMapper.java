package com.sky.mapper;

import com.sky.entity.Orders;
import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    @Select("select * from user where openid=#{openid}")
    User getByOpenid(String openid);

    @Select("select * from user where id=#{id}")
    User getById(Long id);

    void insert(User user);

    /**
     * 根据动态条件查询营业额数据
     *
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}
