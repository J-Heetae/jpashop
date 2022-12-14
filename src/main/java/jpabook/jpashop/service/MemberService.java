package jpabook.jpashop.service;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /*
    * 회원가입
    */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);  //중복 회원 점검
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getUsername());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /*
    * 전체 회원 조회
    */
    public List<Member> findMember() {
        return memberRepository.findAll();
    }

    /*
    * member_id로 회원 조회
    * */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}
