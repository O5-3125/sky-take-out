package com.sky.controller.user;

import com.sky.entity.AddressBook;
import com.sky.result.Result;
import com.sky.service.AddressBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("userAddressBookController")
@RequestMapping("user/addressBook")
@Slf4j
@Api(tags = "c端-地址簿相关接口")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    /**
     * 新增用户地址
     *
     * @param addressBook
     * @return
     */
    @PostMapping()
    @ApiOperation("新增用户地址")
    public Result save(@RequestBody AddressBook addressBook) {

        addressBookService.save(addressBook);
        return Result.success();
    }


    /**
     * 根据id删除地址
     *
     * @param id
     * @return
     */
    @DeleteMapping
    @ApiOperation("根据id删除地址")
    public Result deleteById(Long id) {
        addressBookService.deleteById(id);
        return Result.success();
    }

    /**
     * 根据id修改地址
     *
     * @param addressBook
     * @return
     */
    @PutMapping()
    @ApiOperation("根据id修改地址")
    public Result update(@RequestBody AddressBook addressBook) {
        addressBookService.update(addressBook);
        return Result.success();
    }

    /**
     * 查询当前登录用户的所有地址信息
     *
     * @return
     */
    @GetMapping("list")
    @ApiOperation("查询当前登录用户的所有地址信息")
    public Result<List<AddressBook>> list() {
        List<AddressBook> addressBookList = addressBookService.list();

        return Result.success(addressBookList);
    }

    /**
     * 根据id查询地址
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询地址")
    public Result<AddressBook> getById(@PathVariable("id") Long id) {
        AddressBook addressBook = addressBookService.getById(id);

        return Result.success(addressBook);
    }

    /**
     * 设置默认地址
     *
     * @param id
     * @return
     */
    @PutMapping("/default")
    @ApiOperation("设置默认地址")
    public Result setDefault(Long id) {

        addressBookService.setDefault(id);
        return Result.success();
    }

    /**
     * 获取默认地址
     *
     * @return
     */
    @GetMapping("/default")
    @ApiOperation("获取默认地址")
    public Result<AddressBook> getDefault() {
        AddressBook addressBook = addressBookService.getDefault();
        return Result.success(addressBook);
    }
}
