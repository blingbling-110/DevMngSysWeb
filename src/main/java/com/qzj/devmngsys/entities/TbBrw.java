package com.qzj.devmngsys.entities;

/**
 * 借用公共类：用于封装tb_brw数据表
 *
 * @author qinzijun
 */
public class TbBrw {
    /**
     * 借用单编号
     */
    private String id;

    /**
     * 借用设备编号
     */
    private String devId;

    /**
     * 借用人工号
     */
    private Integer brwerId;

    /**
     * 借用日期
     */
    private String date;

    /**
     * 备注
     */
    private String remark;

    public TbBrw() {
    }

    public TbBrw(String id) {
        this.id = id;
    }

    public TbBrw(String id, String devId, Integer brwerId,
                 String date, String remark) {
        this.id = id;
        this.devId = devId;
        this.brwerId = brwerId;
        this.date = date;
        this.remark = remark;
    }

    //	使用Getters和Setters方法将公共类的私有属性封装起来

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id 要设置的 id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return devId
     */
    public String getDevId() {
        return devId;
    }

    /**
     * @param devId 要设置的 devId
     */
    public void setDevId(String devId) {
        this.devId = devId;
    }

    /**
     * @return brwerId
     */
    public Integer getBrwerId() {
        return brwerId;
    }

    /**
     * @param brwerId 要设置的 brwerId
     */
    public void setBrwerId(Integer brwerId) {
        this.brwerId = brwerId;
    }

    /**
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date 要设置的 date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark 要设置的 remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
