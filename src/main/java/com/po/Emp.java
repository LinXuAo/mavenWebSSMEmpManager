package com.po;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 员工表实体类
 */

public class Emp implements Serializable {
	private Integer eid;
	private String ename;
	private String fname="default.jpg";
	private String sex;
	private String address;
	private Date birthday;
	private Integer depid;//部门编号
	/*与页面相关的临时属性 */
	private String depname;//部门名称
	private Float emoney;//薪资
	//福利方面
	private String[] wids;//福利编号
	private List<Welfare> lswf;//福利集合
	//文件上传
	private MultipartFile pic;
	//日期类型转换
	private String sbirthday;
	public Emp() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//添加专用方法
	public Emp(String ename, String fname, String sex, String address, Date birthday, Integer depid) {
		super();
		this.ename = ename;
		this.fname = fname;
		this.sex = sex;
		this.address = address;
		this.birthday = birthday;
		this.depid = depid;
	}

	
	public Emp(Integer eid, String ename, String fname, String sex, String address, Date birthday, Integer depid,
               String depname, Float emoney, String[] wids, List<Welfare> lswf, MultipartFile pic, String sbirthday) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.fname = fname;
		this.sex = sex;
		this.address = address;
		this.birthday = birthday;
		this.depid = depid;
		this.depname = depname;
		this.emoney = emoney;
		this.wids = wids;
		this.lswf = lswf;
		this.pic = pic;
		this.sbirthday = sbirthday;
	}
	


	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
	public Float getEmoney() {
		return emoney;
	}
	public void setEmoney(Float emoney) {
		this.emoney = emoney;
	}
	public String[] getWids() {
		return wids;
	}
	public void setWids(String[] wids) {
		this.wids = wids;
	}
	public List<Welfare> getLswf() {
		return lswf;
	}
	public void setLswf(List<Welfare> lswf) {
		this.lswf = lswf;
	}
	public MultipartFile getPic() {
		return pic;
	}
	public void setPic(MultipartFile pic) {
		this.pic = pic;
	}
	public String getSbirthday() {
		if (birthday!=null) {
			sbirthday=new SimpleDateFormat("yyyy-MM-dd").format(birthday);
		}
		return sbirthday;
	}
	public void setSbirthday(String sbirthday) {		
		if (sbirthday!=null && !sbirthday.trim().equals("")) {
			try {
				birthday=new SimpleDateFormat("yyyy-MM-dd").parse(sbirthday);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		this.sbirthday = sbirthday;
	}
	@Override
	public String toString() {
		return "Emp [eid=" + eid + ", ename=" + ename + ", fname=" + fname + ", sex=" + sex + ", address=" + address
				+ ", birthday=" + birthday + ", depid=" + depid + ", depname=" + depname + ", emoney=" + emoney
				+ ", wids=" + Arrays.toString(wids) + ", lswf=" + lswf + ", pic=" + pic + ", sbirthday=" + sbirthday
				+ "]";
	}
	

}
