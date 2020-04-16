package com.chococo.mypage.Member.Service;

import java.util.List;

import com.chococo.mypage.Member.VO.CouponVO;
import com.chococo.mypage.Member.VO.MemberVO;

public interface MemberService {
	
	public int create(MemberVO member) throws Exception;
	public MemberVO searchMemberByLogin(MemberVO search) throws Exception;
	public int overlapId(MemberVO member) throws Exception;
	//회원탈퇴
	public void deleteMember(MemberVO member) throws Exception;
	//회원정보 수정
	public void modifyMember(MemberVO member) throws Exception;
	//비밀번호 설정
	public void passwordSetting(MemberVO member) throws Exception;
	//이름, 이메일 중복체크 유무 검사
	public int nameChk(MemberVO member) throws Exception;
	public int emailChk(MemberVO member) throws Exception;
	
	//쿠폰 존재하는지 검사
	public CouponVO mypageCouponExist(CouponVO coupon) throws Exception;
	//쿠폰 등록하기
	public void mypageCouponAdd(CouponVO coupon) throws Exception;
	//사용할 수 있는 쿠폰 불러오기
	public List<CouponVO> mypageCouponList(MemberVO member) throws Exception;
	//사용한 쿠폰 불러오기
	public List<CouponVO> mypageCouponListUsed(MemberVO member) throws Exception;
	//이미 등록한 쿠폰인지 체크할 때
	public int mypageCouponSearch(CouponVO coupon) throws Exception;
	//결제할때 쿠폰 사용함
	public void mypageCouponUse(CouponVO coupon) throws Exception;
}
