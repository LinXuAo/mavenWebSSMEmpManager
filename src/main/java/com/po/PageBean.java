package com.po;

import java.util.List;

/**
 * ��ҳʵ��,Ҳ����ÿҳչʾ���ݵ�ʵ��
 * */
public class PageBean {
	private int rows=5;//Ĭ��ÿҳչʾ����
	private int page=1;//Ĭ��չʾҳ��
	private int maxpage;//ҳ��չʾ��ҳ��,�ܹ��ж���ҳ
    private List<?> pagelist;//	��¼ÿҳ���ݵļ���
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
