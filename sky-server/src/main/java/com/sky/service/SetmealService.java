package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetmealService {

    /**
     * 分页查询
     *
     * @param setmealPageQueryDTO
     * @return
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 根据id查询套餐信息
     *
     * @param id
     * @return
     */
    SetmealVO getById(Long id);

    /**
     * 修改套餐信息
     *
     * @param setmealDTO
     */
    void update(SetmealDTO setmealDTO);

    /**
     * 新增套餐
     *
     * @param setmealDTO
     */
    void save(SetmealDTO setmealDTO);

    /**
     * 启用禁用套餐
     *
     * @param status
     * @param id
     * @return
     */
    void openOrStorp(Integer status, Long id);

    /**
     * 根据ids批量删除套餐
     *
     * @param ids
     * @return
     */
    void deleteBatch( List<Long> ids);


    /**
     * 条件查询
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * 根据id查询菜品选项
     * @param id
     * @return
     */
    List<DishItemVO> getDishItemById(Long id);
}
