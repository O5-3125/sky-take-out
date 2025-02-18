package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    /**
     * 根据菜品id查询口味信息
     *
     * @param dishId
     * @return
     */
    @Select("select * from dish_flavor where dish_id=#{id}")
    List<DishFlavor> getByDishId(Long dishId);

    /**
     * 向口味表中批量添加数据
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);

}
