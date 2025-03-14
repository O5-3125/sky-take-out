package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface DishMapper {

    /**
     * 分页查询
     *
     * @param dishPageQueryDTO
     * @return
     */
    Page<Dish> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 根据分类id查询菜品数量
     *
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * 根据id动态修改属性
     *
     * @param dish
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);

    /**
     * 根据id查询菜品信息
     *
     * @param id
     * @return
     */
    @Select("select * from dish where dish.id=#{id}")
    Dish getById(Long id);

    /**
     * 根据分类id查询菜品信息
     *
     * @return
     */
    @Select("select * from dish where category_id=#{categoryId}")
    List<Dish> getByCategoryId(Long categoryId);

    /**
     * 添加菜品
     *
     * @param dish
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);


    /**
     * 根据套餐id查询菜品
     *
     * @param setmealId
     * @return
     */
    @Select("select a.* from dish a left join setmeal_dish b on a.id = b.dish_id where b.setmeal_id = #{setmealId}")
    List<Dish> getBySetmealId(Long setmealId);

    /**
     * 根据id删除菜品
     *
     * @param id
     */
    @Delete("DELETE from dish where id=#{id}")
    void delete(Long id);

    /**
     * 动态条件查询菜品
     *
     * @param dish
     * @return
     */
    List<Dish> list(Dish dish);


    /**
     * 根据条件统计菜品数量
     *
     * @param map
     * @return
     */
    Integer countByMap(Map map);


}
