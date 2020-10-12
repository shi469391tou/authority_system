package com.hopu.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hopu.domain.*;
import com.hopu.service.*;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MyRealm extends AuthorizingRealm {
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserRoleService userRoleService;
	@Autowired
	private IRoleMenuService roleMenuService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IMenuService menuService;

	// 用户认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		// 先获取token中携带的用户名
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		String username = token.getUsername();
		// 用户查询
		User user = userService.getOne(new QueryWrapper<User>().eq("user_name", username));
		if (user==null) {
			throw new UnknownAccountException("用户名或密码有误！");
		}
		// 返回认证后信息
		ByteSource credentialsSalt = ByteSource.Util.bytes(username + user.getSalt());
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt, getName());
		return info;
	}

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection){
		// 1、获取用户全部信息
		User user = (User) principalCollection.getPrimaryPrincipal();

		// 2、获取当前登录用户的角色列表
		UserRole userRole2 = new UserRole();
		userRole2.setUserId(user.getId());
		List<UserRole> userRoleList = userRoleService.list(new QueryWrapper<UserRole>(userRole2));

		List<String> roleIds = new ArrayList<String>();

		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		for (UserRole Userrole : userRoleList) {
			roleIds.add(Userrole.getRoleId());
			Role role = roleService.getById(Userrole.getRoleId());
			if (role!=null) {
				simpleAuthorizationInfo.addRole(role.getRole());
			}
		}
		// 3、继续获取角色关联的权限列表
		if (!roleIds.isEmpty()) {
			RoleMenu roleMenu2 = new RoleMenu();
			QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<RoleMenu>(roleMenu2);
			queryWrapper.in("role_id", roleIds);

			List<RoleMenu> list = roleMenuService.list(queryWrapper);
			for (RoleMenu Rolemenu : list) {
				Menu menu = menuService.getById(Rolemenu.getMenuId());
				if (menu!=null) {
					simpleAuthorizationInfo.addStringPermission(menu.getPermiss());
				}
			}
		}
		return simpleAuthorizationInfo;
	}
}
