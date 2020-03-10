package com.qzj.devmngsys.service;

import com.qzj.devmngsys.entities.TbBrw;
import com.qzj.devmngsys.repository.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowService {
    @Autowired
    private CommonDao commonDao;

    /**
     * 搜索借用单信息
     *
     * @param id        欲搜索的借用单编号
     * @param devId     欲搜索的借用设备编号
     * @param brwerId   欲搜索的借用人工号
     * @param date      欲搜索的借用日期
     * @param remark    欲搜索的备注
     * @return          包含返回的借用单信息的List集合
     */
    public List<TbBrw> searchBrwInfo(String id, String devId, String brwerId, String date, String remark) {
        if (id != null && !id.isEmpty()) {
            id = id.trim();
        }
        if (devId != null && !devId.isEmpty()) {
            devId = devId.trim();
        }
        if (brwerId != null && !brwerId.isEmpty())
            brwerId = brwerId.trim();
        else
            brwerId = "'%'";
        if (date != null && !date.isEmpty())
            date = date.trim();
        if (remark != null && !remark.isEmpty())
            remark = remark.trim();
        //	搜索借用单信息
        return commonDao.searchBrwInfo(id, devId, brwerId, date, remark);
    }
}
