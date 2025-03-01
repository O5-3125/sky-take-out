package com.sky.mapper;

import com.sky.entity.AddressBook;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AddressBookMapper {

    /**
     * 向地址表中添加地址
     *
     * @param addressBook
     */
    @Insert("insert into address_book(user_id, consignee, sex, phone, province_code, province_name, city_code, " +
            "city_name, district_code, district_name, detail, label, is_default) " +
            "value (#{userId},#{consignee},#{sex},#{phone},#{provinceCode},#{provinceName},#{cityCode}," +
            "#{cityName},#{districtCode},#{districtName},#{detail},#{label},#{isDefault})")
    void save(AddressBook addressBook);

    /**
     * 根据id删除地址
     *
     * @param id
     */
    @Delete("DELETE from address_book where id=#{id}")
    void deleteById(Long id);

    /**
     * 根据id修改地址
     *
     * @param addressBook
     */
    void update(AddressBook addressBook);

    /**
     * 查询当前登录用户的所有地址信息
     *
     * @param userId
     * @return
     */
    @Select("select  * from address_book where user_id=#{userId}")
    List<AddressBook> list(Long userId);

    /**
     * 根据id查询地址
     *
     * @param id
     * @return
     */
    @Select("select * from address_book where id =#{id}")
    AddressBook getById(Long id);

    /**
     * 设置默认地址
     *
     * @param id
     */
    @Update("update  address_book set is_default =1  where id=#{id}")
    void setDefault(Long id);

    /**
     * 获取默认地址
     *
     * @return
     */
    @Select("select * from address_book where user_id = #{userId} and is_default = 1 ")
    AddressBook getDefault(Long userId);
}
