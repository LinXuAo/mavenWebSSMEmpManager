package com.po;

import java.util.List;

/**
 * 分页实体,也就是每页展示内容的实体
 * */
public class PageBean {
	private int rows=5;//默认每页展示数据
	private int page=1;//默认展示页面
	private int maxpage;//页面展示总页数,总共有多少页
    private List<?> pagelist;//	记录每页数据的集合
	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PageBean(int rows, int page, int maxpage, List<?> pagelist) {
		super();
		this.rows = rows;
		this.page = page;
		this.maxpage = maxpage;
		this.pagelist = pagelist;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getMaxpage() {
		return maxpage;
	}
	public void setMaxpage(int maxpage) {
		this.maxpage = maxpage;
	}
	public List<?> getPagelist() {
		return pagelist;
	}
	public void setPagelist(List<?> pagelist) {
		this.pagelist = pagelist;
	}
	@Override
	public String toString() {
		return "PageBean [rows=" + rows + ", page=" + page + ", maxpage=" + maxpage + ", pagelist=" + pagelist + "]";
	}
    

}
