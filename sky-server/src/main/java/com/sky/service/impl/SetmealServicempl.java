package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.exception.SetmealEnableFailedException;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class SetmealServicempl implements SetmealService {
    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;

    /**
     * 分页查询
     *
     * @param setmealPageQueryDTO
     * @return
     */
    public PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(), setmealPageQueryDTO.getPageSize());
        Page<Setmeal> page = setmealMapper.pageQuery(setmealPageQueryDTO);

        long total = page.getTotal();
        List<Setmeal> setmealList = page.getResult();
        return new PageResult(total, setmealList);
    }

    /**
     * 根据id查询套餐信息
     *
     * @param id
     * @return
     */
    public SetmealVO getById(Long id) {
        Setmeal setmeal = setmealMapper.getById(id);

        SetmealVO setmealVO = new SetmealVO();

        BeanUtils.copyProperties(setmeal, setmealVO);
        return setmealVO;
    }

    /**
     * 修改套餐信息
     *
     * @param setmealDTO
     */
    @Transactional
    public void update(SetmealDTO setmealDTO) {

        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        //修改套餐表
        setmealMapper.update(setmeal);
        //获取套餐id
        Long setmealId = setmeal.getId();
        //根据套餐id删除套餐关系
        setmealDishMapper.deleteBySetmealId(setmealId);
        //取出套餐菜品关系
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        //添加套餐id
        setmealDishes.forEach(setmealDish -> {
            setmealDish.setSetmealId(setmealId);
        });
        //向关系表添加多条关系
        setmealDishMapper.insertBatch(setmealDishes);

    }

    /**
     * 新增套餐和套餐菜品关系
     *
     * @param setmealDTO
     */
    @Transactional
    public void save(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        //向套餐表插入套餐
        setmealMapper.insert(setmeal);

        //获取套餐id
        Long setmealId = setmeal.getId();

        //取出套餐菜品关系
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();

        //添加套餐id
        setmealDishes.forEach(setmealDish -> {
            setmealDish.setSetmealId(setmealId);
        });


        //向关系表添加多条关系
        setmealDishMapper.insertBatch(setmealDishes);
    }

    /**
     * 启用禁用套餐
     *
     * @param status
     * @param id
     * @return
     */
    public void openOrStorp(Integer status, Long id) {

        //如果起售套餐，检测套餐内菜品
        if (Objects.equals(status, StatusConstant.ENABLE)) {

            List<Dish> dishes = dishMapper.getBySetmealId(id);
            if (dishes != null && !dishes.isEmpty()) {
                dishes.forEach(dish -> {
                    //若套餐内包含未启售菜品，无法启售
                    if (Objects.equals(dish.getStatus(), StatusConstant.DISABLE)) {
                        throw new SetmealEnableFailedException(MessageConstant.SETMEAL_ENABLE_FAILED);
                    }
                });
            }
        }

        Setmeal setmeal = new Setmeal().builder().status(status).id(id).build();
        setmealMapper.update(setmeal);
    }

    /**
     * 根据ids批量删除套餐
     *
     * @param ids
     * @return
     */
    public void deleteBatch(List<Long> ids) {
        //如果有起售中的套餐，抛出报错
        ids.forEach(id -> {
            Setmeal setmeal = setmealMapper.getById(id);
            if (Objects.equals(setmeal.getStatus(), StatusConstant.ENABLE)) {
                throw new DeletionNotAllowedException(MessageConstant.SETMEAL_ON_SALE);
            }
        });

        ids.forEach(id -> {
            //删除套餐
            setmealMapper.delete(id);
            //删除套餐关系
            setmealDishMapper.deleteBySetmealId(id);
        });
    }

}
