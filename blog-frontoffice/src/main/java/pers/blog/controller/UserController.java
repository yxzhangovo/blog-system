package pers.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.dto.AddUserDto;
import pers.blog.domain.dto.UpdateUserDto;
import pers.blog.service.UserService;

@RestController
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 获取用户列表
     * @param pageNum
     * @param pageSize
     * @param userName
     * @param phonenumber
     * @param status
     * @return
     */
    @GetMapping("/list")
    public ResponseResult getUserList(Integer pageNum, Integer pageSize, String userName, String phonenumber, String status) {
        return userService.getUserList(pageNum, pageSize, userName, phonenumber, status);
    }

    /**
     * 添加用户
     * @param userDto
     * @return
     */
    @PostMapping
    public ResponseResult addUser(@RequestBody AddUserDto userDto) {
        return userService.addUser(userDto);
    }

    /**
     * 删除用户
     * @param ids
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseResult deleteUser(@PathVariable("id") String ids) {
        return userService.deleteUsers(ids);
    }

    /**
     * 查询对应用户信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult getUserInfo(@PathVariable Long id) {
        return userService.getUserInfo(id);
    }

    /**
     * 更新用户
     * @param userDto
     * @return
     */
    @PutMapping
    public ResponseResult updateUser(@RequestBody UpdateUserDto userDto) {
        return userService.updateUser(userDto);
    }

}
