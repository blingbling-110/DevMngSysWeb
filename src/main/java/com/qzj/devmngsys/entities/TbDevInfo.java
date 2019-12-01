package com.qzj.devmngsys.entities;

/**
 * 	设备信息公共类：用于封装tb_devinfo数据表
 * @author qinzijun
 *
 */
public class TbDevInfo {
    /**
     * 	设备编号
     */
    private String id;

    /**
     * 	设备名称
     */
    private String name;

    /**
     * 	设备状态
     */
    private String status;

    /**
     * 	设备描述
     */
    private String des;

    /**
     * 	备注
     */
    private String remark;

    /**
     * 	请求人的工号
     */
    private String req;

    public TbDevInfo() {
    }

    public TbDevInfo(String id) {
        this.id = id;
    }

    public TbDevInfo(String id, String name, String status,
                     String des, String remark) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.des = des;
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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 要设置的 name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 要设置的 status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return des
     */
    public String getDes() {
        return des;
    }

    /**
     * @param des 要设置的 des
     */
    public void setDes(String des) {
        this.des = des;
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

    /**
     * @return req
     */
    public String getReq() {
        return req;
    }

    /**
     * @param req 要设置的 req
     */
    public void setReq(String req) {
        this.req = req;
    }

    /**
     * 	重写toString()方法，只输出设备名称
     */
    public String toString() {
        return getName();
    }

    /**
     * 	重写hashCode()方法，散列值用于提高查找对象的效率
     */
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((id == null)? 0 : id.hashCode());
        result = PRIME * result + ((name == null)? 0 : name.hashCode());
        result = PRIME * result + ((status == null)? 0 : status.hashCode());
        result = PRIME * result + ((des == null)? 0 : des.hashCode());
        result = PRIME * result + ((remark == null)? 0 : remark.hashCode());
        result = PRIME * result + ((req == null)? 0 : req.hashCode());
        return result;
    }

    /**
     * 	重写equals方法，用于比较两个对象是否相等
     */
    public boolean equals(Object obj) {
        if(this == obj)//	检查两者是否指向同一内存地址
            return true;
        if(obj == null)//	检查obj是否为空
            return false;
        if(getClass() != obj.getClass())//	检查两者的运行时类是否相同
            return false;
        final TbDevInfo other = (TbDevInfo) obj;//	强制转换为本公共类对象
        if(id == null) {
            if(other.id != null)
                return false;
        }else if(!id.equals(other.id))
            return false;
        if(name == null) {
            if(other.name != null)
                return false;
        }else if(!name.equals(other.name))
            return false;
        if(status == null) {
            if(other.status != null)
                return false;
        }else if(!status.equals(other.status))
            return false;
        if(des == null) {
            if(other.des != null)
                return false;
        }else if(!des.equals(other.des))
            return false;
        if(remark == null) {
            if(other.remark != null)
                return false;
        }else if(!remark.equals(other.remark))
            return false;
        if(req == null) {
            if(other.req != null)
                return false;
        }else if(!req.equals(other.req))
            return false;
        return true;
    }
}
