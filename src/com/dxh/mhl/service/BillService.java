package com.dxh.mhl.service;

import com.dxh.mhl.dao.BillDAO;
import com.dxh.mhl.dao.MultiTableDAO;
import com.dxh.mhl.domain.Bill;
import com.dxh.mhl.domain.MultiTableBean;

import java.util.List;
import java.util.UUID;

public class BillService {
    private BillDAO billDAO = new BillDAO();
    private MenuService menuService = new MenuService();
    private DiningTableService diningTableService = new DiningTableService();
    private MultiTableDAO multiTableDAO = new MultiTableDAO();


    public boolean orderMenu(int menuId, int nums, int diningTableId) {
        String billID = UUID.randomUUID().toString();

        int update = billDAO.update("insert into bill values(null,?,?,?,?,?,now(),'未结账')",
                billID, menuId, nums,
                menuService.getMenuById(menuId).getPrice().intValue() * nums, diningTableId);

        if (update <= 0) {
            return false;
        }

        // state
        return diningTableService.updateDiningTableState(diningTableId,"就餐中");
    }



    // get bills
    public List<Bill> list() {
        return billDAO.queryMulti("select * from bill",Bill.class);
    }

    //返回所有的账单并带有菜品名,价格， 提供给View调用
    public List<MultiTableBean> list2() {
        return multiTableDAO.queryMulti("SELECT bill.*, NAME " +
                "FROM bill, menu " +
                "WHERE bill.menuId = menu.id", MultiTableBean.class);
    }

    //
    public boolean hasPayBillByDiningTableId(int diningTableId) {
        Bill bill = billDAO.querySingle("SELECT * FROM bill WHERE diningTableId=? AND state = '未结账' LIMIT 0, 1"
                , Bill.class, diningTableId);
        return bill != null;
    }

    public boolean payBill(int diningTableId,String payMode) {
        int update = billDAO.update("update bill set state=? where diningTableId=? and state='未结账'", payMode, diningTableId);

        if (update <= 0) return false; // 未更新则失败

        if (!diningTableService.updateDiningTableStateToFree(diningTableId,"空")) {
            return false;
        }

        return true;
    }

}
