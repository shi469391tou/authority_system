package com.hopu.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hopu.domain.Menu;
import com.hopu.domain.RoleMenu;
import com.hopu.domain.User;
import com.hopu.service.IMenuService;
import com.hopu.service.IRoleMenuService;
import com.hopu.utils.IconFontUtils;
import com.hopu.utils.PageEntity;
import com.hopu.utils.ResponseEntity;
import com.hopu.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.hopu.utils.ResponseEntity.success;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IRoleMenuService roleMenuService;

    @RequestMapping("/tolistPage")
    public String listPage(String id, Model model) {
        List<Menu> menus = menuService.list(new QueryWrapper<Menu>().eq("pid", id).orderByAsc("seq"));
        model.addAttribute("menus", menus);
        return "admin/menu/menu_list";
    }

    /**
     *  菜单数据
     */
    @ResponseBody
    @RequestMapping("/list")
    public PageEntity menuList(int page, int limit, Menu menu) {
        List<Menu> list = menuService.list(new QueryWrapper<Menu>().eq("pid", "0").orderByAsc("seq"));
        List<Menu> list2 = findChildrens2(list, null);
        return new PageEntity(list2.size(), list2);
    }
    // 查询对应的子菜单
    private List<Menu> findChildrens2(List<Menu> list, List<Menu> list2){
        if (list2==null) {
            list2 = new ArrayList<Menu>();
        }
        for (Menu menu : list) {
            list2.add(menu);
            List<Menu> list3 = menuService.list(new QueryWrapper<Menu>(new Menu()).eq("pid", menu.getId()).orderByAsc("seq"));
            if (list3!=null && !list3.isEmpty()) {
                // 递归查询子菜单
                findChildrens2(list3, list2);
            }
        }
        return list2;
    }

    /**
     * 向添加页面跳转
     */
    @RequestMapping("/toAddPage")
    public String addPage(Model model){
        //父级菜单
        List<Menu> list = menuService.list(new QueryWrapper<Menu>(new Menu()).eq("pid", '0'));
        findChildrens(list);

        //图标
        List<String> iconFont = IconFontUtils.getIconFont();

        model.addAttribute("iconFont", iconFont);
        model.addAttribute("list", list);
        return "admin/menu/menu_add";
    }
    private void findChildrens(List<Menu> list){
        for (Menu menu : list) {
            List<Menu> list2 = menuService.list(new QueryWrapper<Menu>(new Menu()).eq("pid", menu.getId()));
            if (list2!=null) {
                menu.setNodes(list2);
            }
        }
    }
    /**
     * 保存
     */
    @ResponseBody
    @RequestMapping("/save")
    public ResponseEntity addMenu(Menu menu){
        menu.setId(UUIDUtils.getID());
        menu.setCreateTime(new Date());
        menuService.save(menu);
        return success();
    }


    /**
     * 跳转修改界面
     */
    @RequestMapping("/toUpdatePage")
    public String adminPage(String id, Model model){
        Menu menu = menuService.getById(id);
        model.addAttribute("menu", menu);

        List<Menu> list = menuService.list(new QueryWrapper<Menu>(new Menu()).eq("pid", '0').orderByAsc("seq"));
        findChildrens(list);

        //图标
        List<String> iconFont = IconFontUtils.getIconFont();

        model.addAttribute("iconFont", iconFont);
        model.addAttribute("list", list);
        return "admin/menu/menu_update";
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("update")
    public ResponseEntity updateMenu(Menu menu){
        menu.setUpdateTime(new Date());
        menuService.updateById(menu);
        return success();
    }

    /**
     * 删除（支持批量删除）
     */
    @ResponseBody
    @RequestMapping("/delete")
    public ResponseEntity delete(@RequestBody ArrayList<Menu> menus){
        List<String> list = new ArrayList<String>();
        for (Menu menu : menus) {
            list.add(menu.getId());
        }
        menuService.removeByIds(list);
        return success();
    }

    /**
     * 权限列表
     */
    @ResponseBody
    @RequestMapping("/MenuList")
    public PageEntity MenuList(String id, Model model){
        List<RoleMenu> roleMenuList = roleMenuService.list(new QueryWrapper<RoleMenu>(new RoleMenu()).eq("role_id", id));
        List<Menu> list = menuService.list(new QueryWrapper<Menu>(new Menu()).eq("pid", '0').orderByAsc("seq"));
        List<JSONObject> list2 = findChildrens3(list, roleMenuList, null);
        return new PageEntity(list2.size(), list2);
    }

    private List<JSONObject> findChildrens3(List<Menu> list, List<RoleMenu> roleMenuList, List<JSONObject> list2){
        if (list2==null) {
            list2 = new ArrayList<JSONObject>();
        }
        //  此处循环的作用就是为了判断角色已有权限，然后添加一个LAY_CHECKED字段，前端layui表格才能自动勾选
        for (Menu menu : list) {
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(menu));

            boolean rs = false;
            for (RoleMenu rolemenu : roleMenuList) {
                if (rolemenu.getMenuId().equals(menu.getId())) {
                    rs = true;
                }
            }
            jsonObject.put("LAY_CHECKED", rs);
            list2.add(jsonObject);
            List<Menu> list3 = menuService.list(new QueryWrapper<Menu>(new Menu()).eq("pid", menu.getId()).orderByAsc("seq"));
            if (list3!=null && !list3.isEmpty()) {
                findChildrens3(list3, roleMenuList, list2);
            }
        }
        return list2;
    }
}
