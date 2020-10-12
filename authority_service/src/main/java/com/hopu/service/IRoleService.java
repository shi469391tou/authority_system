package com.hopu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hopu.domain.Menu;
import com.hopu.domain.Role;

import java.util.List;

public interface IRoleService extends IService<Role> {
    void setMenu(String id, List<Menu> menus);
}
