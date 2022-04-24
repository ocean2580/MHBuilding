package com.dxh.mhl.service;

import com.dxh.mhl.dao.DiningTableDAO;
import com.dxh.mhl.domain.DiningTable;

import java.util.List;

public class DiningTableService { // 业务
    private DiningTableDAO diningTableDAO = new DiningTableDAO();

    public List<DiningTable> list() {
       return diningTableDAO.queryMulti("select id, state from diningTable",
                DiningTable.class);
    }

    // id -> obj
    public DiningTable getDiningTableById(int id) {
        return diningTableDAO.querySingle("select * from diningTable where id = ?",
                DiningTable.class,id);

    }

    public boolean orderDiningTable(int id, String orderName, String orderTel) {
        int update = diningTableDAO.update("update diningTable set state='已预定', orderName=?,orderTel=? where id =? "
                , orderName, orderTel,id);
        return update > 0;
    }

    public boolean updateDiningTableState(int id, String state) {

        int update = diningTableDAO.update("update diningTable set state=? where id=?", state, id);
        return update>0;

    }


    public boolean updateDiningTableStateToFree(int id, String state) {

        int update =
                diningTableDAO.update("update diningTable set state=? , orderName='', orderTel='' where id=?", state, id);
        return update>0;

    }



}