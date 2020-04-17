package com.chococo.mypage.Member.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.chococo.mypage.Member.VO.CouponVO;
import com.chococo.mypage.Member.VO.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Inject
	private SqlSession session;

	@Override
	public int create(MemberVO member) throws Exception {
		return session.insert("Chococo.member.create", member);
	}

	@Override
	public MemberVO searchMemberByLogin(MemberVO search) throws Exception {
		return session.selectOne("Chococo.member.searchMemberByLogin", search);
	}

	@Override
	public void deleteMember(MemberVO member) throws Exception {
		session.delete("Chococo.member.deleteMember", member);
	}

	@Override
	public void modifyMember(MemberVO member) throws Exception {
		session.update("Chococo.member.modifyMember", member);
	}

	@Override
	public void passwordSetting(MemberVO member) throws Exception {
		session.update("Chococo.member.passwordSetting", member);
	}

	@Override
	public int nameChk(MemberVO member) throws Exception {
		return session.selectOne("Chococo.member.nameChk", member);
	}

	@Override
	public int emailChk(MemberVO member) throws Exception {
		return session.selectOne("Chococo.member.emailChk", member);
	}

	@Override
	public CouponVO mypageCouponExist(CouponVO coupon) throws Exception {
		return session.selectOne("Chococo.member.mypageCouponExist", coupon);
	}

	@Override
	public void mypageCouponAdd(CouponVO coupon) throws Exception {
		session.insert("Chococo.member.mypageCouponAdd", coupon);
	}

	@Override
	public List<CouponVO> mypageCouponList(MemberVO member) throws Exception {
		return session.selectList("Chococo.member.mypageCouponList", member);
	}

	@Override
	public List<CouponVO> mypageCouponListUsed(MemberVO member) throws Exception {
		return session.selectList("Chococo.member.mypageCouponListUsed", member);
	}

	@Override
	public int mypageCouponSearch(CouponVO coupon) throws Exception {
		return session.selectOne("Chococo.member.mypageCouponSearch", coupon);
	}

	@Override
	public void mypageCouponUse(CouponVO coupon) throws Exception {
		session.update("Chococo.member.mypageCouponUse", coupon);
	}

}
