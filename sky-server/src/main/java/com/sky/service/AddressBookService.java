package com.sky.service;

import com.sky.entity.AddressBook;

import java.util.List;

public interface AddressBookService {

    /**
     * 新增用户地址
     */
    void save(AddressBook addressBook);

    /**
     * 根据id删除地址
     *
     * @param id
     */
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
     * @return
     */
    List<AddressBook> list();

    /**
     * 根据id查询地址
     *
     * @param id
     * @return
     */
    AddressBook getById(Long id);

    /**
     * 设置默认地址
     *
     * @param id
     * @return
     */
    void setDefault(Long id);

    /**
     * 获取默认地址
     *
     * @return
     */
    AddressBook getDefault();
}
