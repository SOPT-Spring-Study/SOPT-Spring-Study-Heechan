package com.example.demo.controller;

import com.example.demo.model.Member;
import com.example.demo.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("")
    public Member saveMember(@RequestBody Member newMember){
        return memberService.saveMember(newMember);
    }

    @GetMapping("")
    public List<Member> getAllMembers(){
        return memberService.getAllMembers();
    }

    @GetMapping("/{memberId}")
    public Member getMember(@PathVariable("memberId") int memberId){
        return memberService.getMember(memberId);
    }

    @PutMapping("/{memberId}")
    public Member updateMember(@PathVariable("memberId") int memberId,
                               @RequestBody Member updatedMember){
        return memberService.updateMember(memberId, updatedMember);
    }

    @DeleteMapping("/{memberId}")
    public Member deleteMember(@PathVariable("memberId") int memberId){
        return memberService.deleteMember(memberId);
    }
}
