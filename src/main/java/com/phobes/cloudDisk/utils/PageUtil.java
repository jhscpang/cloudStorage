package com.phobes.cloudDisk.utils;

/**
 * 
 * @author �? 分页工具�?
 */
public class PageUtil {

	private int curPage = 1; // 当前是第几页，默认是第一�?
	private long maxPage; // �?共有多少�?
	private long maxRowCount; // �?共有多少�?
	public int rowsPerPage = 10; // 每页多少�?

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public long getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(long maxPage) {
		this.maxPage = maxPage;
	}

	public long getMaxRowCount() {
		return maxRowCount;
	}

	public void setMaxRowCount(long maxRowCount) {
		this.maxRowCount = maxRowCount;
	}

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

}
