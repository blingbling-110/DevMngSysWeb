package com.qzj.devmngsys.entities;

/**
 * 	人员信息公共类：用于封装tb_userinfo数据表
 * @author qinzijun
 *
 */
public class TbUserInfo {
    /**
     * 	工号
     */
    private Integer id;

    /**
     * 	姓名
     */
    private String name;

    /**
     * 	用户名
     */
    private String userid;

    /**
     * 	密码
     */
    private String pwd;

    /**
     * 	职务
     */
    private String pos;

    /**
     * 	部门
     */
    private String dep;

    /**
     * 	邮箱
     */
    private String email;

    /**
     * 	电话
     */
    private String tel;

    /**
     * 	备注
     */
    private String remark;

    /**
     * 	是否拥有管理权限
     */
    private boolean isadmin;

    public TbUserInfo() {
    }

    public TbUserInfo(Integer id) {
        this.id = id;
    }

    public TbUserInfo(Integer id, String name, String userid,
                      String pwd, String pos, String dep, String email,
                      String tel, String remark, boolean isadmin) {
        this.id = id;
        this.name = name;
        this.userid = userid;
        this.pwd = pwd;
        this.pos = pos;
        this.dep = dep;
        this.email = email;
        this.tel = tel;
        this.remark = remark;
        this.isadmin = isadmin;
    }

    //	使用Getters和Setters方法将公共类的私有属性封装起来

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 要设置的 id
     */
    public void setId(Integer id) {
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
     * @return userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid 要设置的 userid
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * @return pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * @param pwd 要设置的 pwd
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * @return pos
     */
    public String getPos() {
        return pos;
    }

    /**
     * @param pos 要设置的 pos
     */
    public void setPos(String pos) {
        this.pos = pos;
    }

    /**
     * @return dep
     */
    public String getDep() {
        return dep;
    }

    /**
     * @param dep 要设置的 dep
     */
    public void setDep(String dep) {
        this.dep = dep;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email 要设置的 email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel 要设置的 tel
     */
    public void setTel(String tel) {
        this.tel = tel;
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
     * @return isadmin
     */
    public boolean isIsadmin() {
        return isadmin;
    }

    /**
     * @param isAdmin 要设置的 isadmin
     */
    public void setIsadmin(boolean isAdmin) {
        this.isadmin = isAdmin;
    }
}
