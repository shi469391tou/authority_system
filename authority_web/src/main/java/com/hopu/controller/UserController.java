package com.hopu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hopu.domain.Role;
import com.hopu.domain.User;
import com.hopu.service.IUserService;
import com.hopu.utils.ResponseEntity;
import com.hopu.utils.ShiroUtils;
import com.hopu.utils.UUIDUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.hopu.utils.ResponseEntity.error;
import static com.hopu.utils.ResponseEntity.success;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService userService;

	// 向用户列表页面跳转
	@RequiresPermissions("user:list")
    @RequestMapping("/tolistPage")
    public String userList(){
        return "admin/user/user_list";
    }

	/**
	 * 分页查询用户列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	public IPage<User> userList(int page, int limit, User user, Model model){
		// 设置分页条件
		Page<User> page2 = new Page<User>(page, limit);
		// QueryWrapper封装查询条件
		QueryWrapper<User> queryWrapper = new QueryWrapper<>(new User());
		if (user!=null){
			if (!StringUtils.isEmpty(user.getUserName())) queryWrapper.like("user_name", user.getUserName());
			if (!StringUtils.isEmpty(user.getTel())) queryWrapper.like("tel", user.getTel());
			if (!StringUtils.isEmpty(user.getEmail())) queryWrapper.like("email", user.getEmail());
		}
		// 分页查询时，带上分页数据以及查询条件对象
		IPage<User> userIPage = userService.page(page2,queryWrapper);
		return userIPage;
	}

	// 向用户添加页面跳转
	@RequestMapping("/toAddPage")
	@RequiresPermissions("user:add")
	public String toAddPage(){
		return "admin/user/user_add";
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	public ResponseEntity addUser(User user){
		// 进行用户名重名查询
		User user2 = userService.getUserByUserName(user.getUserName());
		if (user2!=null) {
			return error("用户名已存在");
		}
		user.setId(UUIDUtils.getID());
		user.setSalt(UUIDUtils.getID());
		ShiroUtils.encPass(user);
		user.setCreateTime(new Date());
		userService.save(user);
		return success();
	}

	/**
	 * 跳转修改界面
	 */
	@RequestMapping("/toUpdatePage")
	@RequiresPermissions("user:update")
	public String toUpdatePage(String id, Model model){
		User user = userService.getById(id);
		model.addAttribute("user", user);
		return "admin/user/user_update";
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public ResponseEntity updateUser(User user){
		ShiroUtils.encPass(user);
		user.setUpdateTime(new Date());
		userService.updateById(user);
		return success();
	}

	/**
	 * 删除（支持批量删除）
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("user:delete")
	public ResponseEntity delete(@RequestBody ArrayList<User> users){
		try {
			List<String> list = new ArrayList<String>();
			for (User user : users) {
				if ("root".equals(user.getUserName())) {
					throw new Exception("root账号不能被删除");
				}
				list.add(user.getId());
			}
			userService.removeByIds(list);
		} catch (Exception e) {
			e.printStackTrace();
			return error(e.getMessage());
		}
		return success();
	}

	/**
	 * 跳转分配角色界面
	 */
	@RequestMapping("/toSetRole")
//	@RequiresPermissions("user:setRole")
	public String toSetRole(String id, Model model){
		model.addAttribute("user_id", id);
		return "admin/user/user_setRole";
	}
	/**
	 * 设置角色
	 */
	@ResponseBody
	@RequestMapping("setRole")
	public ResponseEntity setRole(String id, @RequestBody ArrayList<Role> roles){
		userService.setRole(id, roles);
		return success();
	}
}

