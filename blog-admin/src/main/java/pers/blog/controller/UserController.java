package pers.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.dto.AddUserDto;
import pers.blog.domain.dto.UpdateUserDto;
import pers.blog.service.UserService;

@Api(tags = "用户", description = "用户相关接口")
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
    @ApiOperation(value = "分页查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页数"),
            @ApiImplicitParam(name = "pageSize",value = "一页显示的内容"),
            @ApiImplicitParam(name = "userName",value = "可选, 模糊查询用户名"),
            @ApiImplicitParam(name = "phonenumber",value = "可选, 模糊查询手机号"),
            @ApiImplicitParam(name = "status",value = "可选, 根据状态查询")
    })
    @GetMapping("/list")
    public ResponseResult getUserList(Integer pageNum, Integer pageSize, String userName, String phonenumber, String status) {
        return userService.getUserList(pageNum, pageSize, userName, phonenumber, status);
    }

    /**
     * 添加用户
     * @param userDto
     * @return
     */
    @ApiOperation(value = "添加用户")
    @PostMapping
    public ResponseResult addUser(@RequestBody AddUserDto userDto) {
        return userService.addUser(userDto);
    }

    /**
     * 删除用户
     * @param ids
     * @return
     */
    @ApiOperation(value = "根据id删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "要删除的id, 可能为多个, 类型为字符串格式")
    })
    @DeleteMapping("/{id}")
    public ResponseResult deleteUser(@PathVariable("id") String ids) {
        return userService.deleteUsers(ids);
    }

    /**
     * 查询对应用户信息
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询用户信息")
    @GetMapping("/{id}")
    public ResponseResult getUserInfo(@PathVariable Long id) {
        return userService.getUserInfo(id);
    }

    /**
     * 更新用户
     * @param userDto
     * @return
     */
    @ApiOperation(value = "更新用户")
    @PutMapping
    public ResponseResult updateUser(@RequestBody UpdateUserDto userDto) {
        return userService.updateUser(userDto);
    }

}
