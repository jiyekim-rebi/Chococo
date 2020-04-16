package com.chococo.mypage.Market.VO;

import java.util.List;

/*
 * 2020. 03. 26
 * checkOut에서 order로 데이터 전송할 때 동적 전송(amount 값 변경)을 위한 VO
 */
public class BasketList {
	
	private List<BasketVO> basketLists = null;

	public List<BasketVO> getBasketLists() {
		return basketLists;
	}

	public void setBasketLists(List<BasketVO> basketLists) {
		this.basketLists = basketLists;
	}
	
}
