package com.hopu.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

/**
 * 菜单表对应实体类
 */
@TableName("t_menu")
public class Menu extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String pid;  // 父级id。0：表示顶级目录
    private String menuName; // 菜单名称
    private Integer menuType; // 菜单类型。1：顶级目录；2：菜单;3：按钮
    private String menuImg; // 当前菜单对应的图标样式
    private String permiss; // 权限标识符
    private String url; // 点击菜单要跳转的路径地址
    private String functionImg;  //  打开跳转地址后对应菜单的图标样式
    private String seq; // 按钮的顺序。从1，2，3开始

    /**
     * 子项
     * @TableField(exist=false) 表示忽略映射此字段
     */
    @TableField(exist=false)
    private List<Menu> nodes;

    public List<Menu> getNodes() {
		return nodes;
	}

	public void setNodes(List<Menu> nodes) {
		this.nodes = nodes;
	}

	public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public String getMenuImg() {
        return menuImg;
    }

    public void setMenuImg(String menuImg) {
        this.menuImg = menuImg;
    }

    public String getPermiss() {
        return permiss;
    }

    public void setPermiss(String permiss) {
        this.permiss = permiss;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFunctionImg() {
        return functionImg;
    }

    public void setFunctionImg(String functionImg) {
        this.functionImg = functionImg;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "pid='" + pid + '\'' +
                ", menuName='" + menuName + '\'' +
                ", menuType=" + menuType +
                ", menuImg='" + menuImg + '\'' +
                ", permiss='" + permiss + '\'' +
                ", url='" + url + '\'' +
                ", functionImg='" + functionImg + '\'' +
                ", seq='" + seq + '\'' +
                ", nodes=" + nodes +
                '}';
    }
}
