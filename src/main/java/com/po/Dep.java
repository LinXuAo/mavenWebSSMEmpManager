package com.po;

import java.io.Serializable;

/**
 * ���ű�ʵ����
 * */
public class Dep implements Serializable{
	private Integer depid;//���ű��
	private String depname;//��������
	public Dep() {
		super();
		// TODO Auto-generated constructor stub
	}
	//��ӵ�ר�÷���
	public Dep(String depname) {
		super();
		this.depname = depname;
	}

	public Dep(Integer depid, String depname) {
		super();
		this.depid = depid;
		this.depname = depname;
	}
	public Integer getDepid() {
		return depid;
	}
	public void setDepid(Integer depid) {
		this.depid = depid;
	}
	public String getDepname() {
		return depname;
	}
	public void setDepname(String depname) {
		this.depname = depname;
	}
	@Override
	public String toString() {
		return "Dep [depid=" + depid + ", depname=" + depname + "]";
	}
	

}
