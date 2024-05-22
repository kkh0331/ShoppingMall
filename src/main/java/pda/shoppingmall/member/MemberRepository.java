package pda.shoppingmall.member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemberRepository {

    private static final Logger log = LoggerFactory.getLogger(MemberRepository.class);
    private Map<String, Member> memberTable = new HashMap<>();

//    @Autowired
//    private DataSource dataSource;

    @Autowired
    private EntityManager entityManager;

//    public void makeConnection(){
//        DataSourceUtils.getConnection(dataSource);
//    }

    public void save(Member member) {
//        memberTable.put(member.getUserId(), member);
//
//        Member savedMember = memberTable.get(member.getUserId());
        log.info("save : {}", member);
        entityManager.persist(member);
    }

    public Member findByUserId(String userId){
        String jpql = "SELECT m FROM Member m WHERE m.userId = :userId";
        return entityManager
                .createQuery(jpql, Member.class)
                .setParameter("userId", userId)
                .getResultList()
                .stream()
                .findAny()
                .orElse(null);
//        return entityManager.createQuery(jpql, Member.class)
//                .setParameter("userId", userId)
//                .getSingleResult();
    }

    public Member findById(int id){
        return entityManager.find(Member.class, id);
//        return null;
    }
}
