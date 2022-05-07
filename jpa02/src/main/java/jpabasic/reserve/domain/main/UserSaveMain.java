package jpabasic.reserve.domain.main;

import jpabasic.reserve.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class UserSaveMain {


    private static final Logger log = LoggerFactory.getLogger(UserSaveMain.class);
    public static void main(String[] args) {

        /* DB 연동을 위한 기반 생성 */
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpabegin");

        /* 실제 DB 연동 */
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        /* DB Transaction 처리 */
        EntityTransaction transaction = entityManager.getTransaction();

        try {

            log.info("Insert 실제 실행 시점은?");

            transaction.begin();

            User user = new User("user4@user.com", "user", LocalDateTime.now());

            /* DB에 객체 저장 */
            log.info("EntityManager.persist 호출 전");
            entityManager.persist(user);
            log.info("EntityManager.persist 호출 후");

            log.info("EntityManager.commit 호출 전");
            transaction.commit();
            log.info("EntityManager.commit 호출 후");

        } catch (Exception e) {
            log.error("Exception {} 발생 : {}", e.getClass().getName(), e.getMessage());
            transaction.rollback();

        } finally {
            /* 사용이 완료되면 close 처리 해주어야 함. */
            entityManager.close();
        }

        /* close 필요. close 후 사용했던 자원들을 반납. */
        entityManagerFactory.close();
    }
}
