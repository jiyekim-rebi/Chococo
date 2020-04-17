package com.chococo.mypage.Member.Service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.chococo.mypage.Member.DAO.MemberDAO;
import com.chococo.mypage.Member.VO.CouponVO;
import com.chococo.mypage.Member.VO.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Inject
	private MemberDAO dao;

	@Override
	public int create(MemberVO member) throws Exception {
		return dao.create(member);
	}

	@Override
	public MemberVO searchMemberByLogin(MemberVO search) throws Exception {
		// TODO Auto-generated method stub
		return dao.searchMemberByLogin(search);
	}

	@Override
	public void deleteMember(MemberVO member) throws Exception {
		dao.deleteMember(member);
	}

	@Override
	public void modifyMember(MemberVO member) throws Exception {
		dao.modifyMember(member);
	}

	@Override
	public void passwordSetting(MemberVO member) throws Exception {
		dao.passwordSetting(member);
	}

	@Override
	public int nameChk(MemberVO member) throws Exception {
		return dao.nameChk(member);
	}

	@Override
	public int emailChk(MemberVO member) throws Exception {
		return dao.emailChk(member);
	}

	@Override
	public CouponVO mypageCouponExist(CouponVO coupon) throws Exception {
		return dao.mypageCouponExist(coupon);
	}

	@Override
	public void mypageCouponAdd(CouponVO coupon) throws Exception {
		dao.mypageCouponAdd(coupon);
	}

	@Override
	public List<CouponVO> mypageCouponList(MemberVO member) throws Exception {
		return dao.mypageCouponList(member);
	}

	@Override
	public List<CouponVO> mypageCouponListUsed(MemberVO member) throws Exception {
		return dao.mypageCouponListUsed(member);
	}

	@Override
	public int mypageCouponSearch(CouponVO coupon) throws Exception {
		return dao.mypageCouponSearch(coupon);
	}

	@Override
	public void mypageCouponUse(CouponVO coupon) throws Exception {
		dao.mypageCouponUse(coupon);
	}

}
