package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.entity.AddressBook;
import com.sky.mapper.AddressBookMapper;
import com.sky.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AddressBookServiceImpl implements AddressBookService {
    @Autowired
    public AddressBookMapper addressBookMapper;

    /**
     * 添加地址
     *
     * @param addressBook
     */
    public void save(AddressBook addressBook) {
        Long userId = BaseContext.getCurrentId();
        addressBook.setUserId(userId);

        addressBook.setIsDefault(0);

        addressBookMapper.save(addressBook);
    }

    /**
     * 根据id删除地址
     *
     * @param id
     */
    public void deleteById(Long id) {
        addressBookMapper.deleteById(id);
    }

    /**
     * 根据id修改地址
     *
     * @param addressBook
     */
    public void update(AddressBook addressBook) {
        addressBookMapper.update(addressBook);
    }

    /**
     * 查询当前登录用户的所有地址信息
     *
     * @return
     */
    public List<AddressBook> list() {
        Long userId = BaseContext.getCurrentId();
        List<AddressBook> addressBookList = addressBookMapper.list(userId);
        return addressBookList;
    }

    /**
     * 根据id查询地址
     *
     * @param id
     * @return
     */
    public AddressBook getById(Long id) {
        AddressBook addressBook = addressBookMapper.getById(id);

        return addressBook;
    }

    /**
     * 设置默认地址
     *
     * @param id
     * @return
     */
    public void setDefault(Long id) {
        addressBookMapper.setDefault(id);
    }

    /**
     * 获取默认地址
     *
     * @return
     */
    public AddressBook getDefault() {
        Long userId = BaseContext.getCurrentId();

        AddressBook addressBook = addressBookMapper.getDefault(userId);

        return addressBook;
    }

}
