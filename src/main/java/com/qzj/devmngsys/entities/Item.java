package com.qzj.devmngsys.entities;

/**
 * Item公共类：对id、name和jobNum属性进行封装
 *
 * @author qinzijun
 */
public class Item {
    /**
     * id：数据库中某项的id值
     */
    private String id;
    /**
     * name：数据库中某项的name值
     */
    private String name;
    /**
     * jobNum：数据库中某用户的id值（工号）
     */
    private Integer jobNum;

    /**
     * 默认构造方法
     */
    public Item() {
    }

    /**
     * 包含所有属性初始化的构造方法
     *
     * @param id     该项在数据库中的id值
     * @param name   该项在数据库中的name值
     * @param jobNum 该用户的工号
     */
    public Item(String id, String name, Integer jobNum) {
        this.id = id;
        this.name = name;
        this.jobNum = jobNum;
    }

    //	使用Getters和Setters方法将Item公共类的私有属性封装起来

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
     * @return jobNum
     */
    public Integer getJobNum() {
        return jobNum;
    }

    /**
     * @param jobNum 要设置的 jobNum
     */
    public void setJobNum(Integer jobNum) {
        this.jobNum = jobNum;
    }

    /**
     * 重写toString()方法，只能输出name属性
     */
    public String toString() {
        return getName();
    }
}
