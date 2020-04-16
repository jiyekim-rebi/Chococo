package com.chococo.mypage;

import java.util.Date;

public class RecentList extends MarketVO {
	
	private Date recentData;

	public Date getRecentData() {
		return recentData;
	}

	public void setRecentData(Date recentData) {
		this.recentData = recentData;
	}

	@Override
	public String toString() {
		return "RecentList [recentData=" + recentData + "]";
	}
	
}
