package com.dxh.mhl.service;

import com.dxh.mhl.dao.MenuDAO;
import com.dxh.mhl.domain.Menu;

import java.util.List;

public class MenuService {
    private MenuDAO menuDAO = new MenuDAO();

    public List<Menu> list() {
        return menuDAO.queryMulti("select * from menu ",Menu.class);
    }


    public Menu getMenuById(int id) {
        return menuDAO.querySingle("select * from diningTable where id = ?",
                Menu.class,id);
    }


}
