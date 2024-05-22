package pda.shoppingmall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pda.shoppingmall.member.Member;
import pda.shoppingmall.member.MemberJPARepository;

import java.util.Optional;

@Component
public class CustomApplicationRunner implements ApplicationRunner {

    @Autowired
    private MemberJPARepository memberJPARepository;

    public void run(ApplicationArguments args) throws Exception{
        Optional<Member> resultMember = memberJPARepository.findByUserId("test_id28");
        if(resultMember.isEmpty()){
            System.out.println("null!!!!");
        } else{
            System.out.println(resultMember);
        }
    }

}