package com.qzj.devmngsys.service;

import com.qzj.devmngsys.entities.TbDevInfo;
import com.qzj.devmngsys.entities.TbUserInfo;
import com.qzj.devmngsys.repository.DashboardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class DashboardService {
    @Autowired
    private DashboardDao dashboardDao;

    /**
     * 获取查询人所借用设备信息的哈希图，
     * 该哈希图以"设备编号_请求人姓名"为键，设备信息对象为值。
     * 若无人请求，则以"设备编号_no_request"为键。
     *
     * @param id 查询人工号
     * @return 查询人所借用设备信息的哈希图
     */
    public LinkedHashMap<String, TbDevInfo> getMyDev(Integer id) {
        LinkedHashMap<String, TbDevInfo> myDev = new LinkedHashMap<>();
        List<TbDevInfo> myDevInfo = dashboardDao.getMyDevInfo(id);
        if (myDevInfo != null && !myDevInfo.isEmpty()) {
            for (TbDevInfo tbDevInfo : myDevInfo) {
                String request_id = tbDevInfo.getReq();
                if (request_id != null && !request_id.isEmpty()) {
                    TbUserInfo tbUserInfo = dashboardDao.getTbUserInfo(Integer.parseInt(request_id));
                    myDev.put(tbDevInfo.getId() + "_" + tbUserInfo.getName(), tbDevInfo);
                } else {
                    String key = tbDevInfo.getId() + "_no_request";
                    myDev.put(key, tbDevInfo);
                }
            }
        }
        return myDev;
    }
}
