package com.qzj.devmngsys.entities;

/**
 * 	归还公共类：用于封装tb_rtn数据表
 * @author qinzijun
 *
 */
public class TbRtn {
	/**
	 * 	归还单编号
	 */
	private String id;
	
	/**
	 * 	归还设备编号
	 */
	private String devId;
	
	/**
	 * 	归还人工号
	 */
	private Integer rtnerId;
	
	/**
	 * 	归还日期
	 */
	private String date;
	
	/**
	 * 	备注
	 */
	private String remark;
	
	public TbRtn() {
	}

	public TbRtn(String id) {
		this.id = id;
	}

	public TbRtn(String id, String devId, Integer rtnerId, 
			String date, String remark) {
		this.id = id;
		this.devId = devId;
		this.rtnerId = rtnerId;
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
	 * @return rtnerId
	 */
	public Integer getRtnerId() {
		return rtnerId;
	}

	/**
	 * @param rtnerId 要设置的 rtnerId
	 */
	public void setRtnerId(Integer rtnerId) {
		this.rtnerId = rtnerId;
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
