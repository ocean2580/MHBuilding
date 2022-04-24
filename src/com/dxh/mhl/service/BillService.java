package com.dxh.mhl.service;

import com.dxh.mhl.dao.BillDAO;
import com.dxh.mhl.domain.Menu;

import java.util.UUID;

public class BillService {
    private BillDAO billDAO = new BillDAO();
    private MenuService menuService = new MenuService();
    private DiningTableService diningTableService = new DiningTableService();


    public boolean orderMenu(int menuId, int nums, int diningTableId) {
        String billID = UUID.randomUUID().toString();

        int update = billDAO.update("insert into bill values(null,?,?,?,?,?,now(),'未结账')",
                billID, menuId, nums,
                menuService.getMenuById(menuId).getPrice() * nums, diningTableId);

        if (update <= 0) {
            return false;
        }

        // state
        return diningTableService.updateDiningTableState(diningTableId,"就餐中");

    }




}
