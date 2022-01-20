package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;


    @Autowired  //스프링 컨테이너의 memberService와 연결을 시켜줌. 의존관계 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    // 필드 주입 -> 별로 안 좋은 방법.
    // 생성자 주입 -> 권장.
    // setter 주입 -> public으로 노출이 되어있음.



    //URL 쳐서 들어가지는 거(조회하는 방식)
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    //post 방식으로 들어가지는 거(form에 넣어서)
    @PostMapping("members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}

