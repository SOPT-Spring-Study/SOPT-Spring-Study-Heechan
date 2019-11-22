package com.example.demo.service;

import com.example.demo.mapper.MemberMapper;
import com.example.demo.model.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public boolean saveMember(Member newMember) {
        return memberMapper.insertMember(newMember) != 0;
    }

    public List<Member> getAllMembers() {
        return memberMapper.getAllMembers();
    }

    public Member getMemberById(int memberId) {
        return memberMapper.getMemberById(memberId);
    }

    public boolean putMember(int memberId, Member puttedMember) {
        return memberMapper.updateMember(memberId, puttedMember) != 0;
    }

    public boolean deleteMember(int memberId) {
        return memberMapper.deleteMember(memberId) != 0;
    }
}