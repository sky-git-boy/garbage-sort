package com.sky.controller.system;

import com.sky.constants.Constants;
import com.sky.controller.BaseController;
import com.sky.domain.Menu;
import com.sky.dto.MenuDto;
import com.sky.service.MenuService;
import com.sky.utils.ShiroSecurityUtils;
import com.sky.vo.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sky
 * @create 2021-05-25 9:30
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController extends BaseController {
    @Autowired
    private MenuService menuService;

    /**
     * 查询所有菜单及权限信息
     */
    @GetMapping("listAllMenus")
    public AjaxResult listAllMenus(MenuDto menuDto){
        List<Menu> list = this.menuService.listAllMenus(menuDto);
        return AjaxResult.success(list);
    }


    /**
     * 查询菜单的下拉树
     */
    @GetMapping("selectMenuTree")
    public AjaxResult selectMenuTree(){
        MenuDto menuDto = new MenuDto();
        menuDto.setStatus(Constants.STATUS_TRUE); // 只查询可用的
        return AjaxResult.success(this.menuService.listAllMenus(menuDto));
    }

    /**
     * 添加菜单
     */
    @PostMapping("addMenu")
    public AjaxResult addMenu(@Validated MenuDto menuDto){
        menuDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.menuService.addMenu(menuDto));
    }

    /**
     * 修改菜单
     */
    @PutMapping("updateMenu")
    public AjaxResult updateMenu(@Validated MenuDto menuDto){
        menuDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.menuService.updateMenu(menuDto));
    }

    /**
     * 根据菜单ID查询一个
     */
    @GetMapping("getMenuById/{menuId}")
    public AjaxResult getMenuById(@PathVariable Long menuId){
        Menu menu = this.menuService.getOne(menuId);
        return AjaxResult.success(menu);
    }

    /**
     * 根据菜单ID删除
     */
    @DeleteMapping("deleteMenuById/{menuId}")
    public AjaxResult deleteMenuById(@PathVariable Long menuId){
        //删除之前要判断当前菜单有没有子节点
        if(this.menuService.hasChildByMenuId(menuId)){
            return AjaxResult.fail("当前要删除的菜单有子节点，请先删除子节点");
        }
        return AjaxResult.toAjax(this.menuService.deleteMenuById(menuId));
    }

    /**
     * 根据角色ID查询已分配菜单ID[只查子节点]
     */
    @GetMapping("getMenuIdsByRoleId/{roleId}")
    public AjaxResult getMenuIdsByRoleId(@PathVariable Long roleId) {
        List<Long> menusId = this.menuService.getMenuIdsByRoleId(roleId);
        return AjaxResult.success(menusId);
    }


}
