package jpabasic.reserve.domain.main;

import jpabasic.reserve.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


public class UserGetMain {
    public static void main(String[] args) {

        /* DB 연동을 위한 기반 생성 */
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpabegin");

        /* 실제 DB 연동 */
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        /* DB Transaction 처리 */
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            System.out.println("Select 실제 실행 시점은?");

            /* 트랜잭션 시작 */
            transaction.begin();

            /* DB 객체 Read */
            System.out.println("EntityManager.find 호출 전");
            User user = entityManager.find(User.class, "user@user.com");
            System.out.println("EntityManager.find 호출 후");

            if(user == null) {
                System.out.println("No user");
            } else {
                System.out.printf("User exists : email=%s, name=%s, createDate%s\n",
                        user.getEmail(), user.getName(), user.getCreateDate());
            }

            /* 트랜잭션 커밋 */
            System.out.println("EntityManager.commit 호출 전");
            transaction.commit();
            System.out.println("EntityManager.commit 호출 후");

        } catch (Exception e) {
            e.printStackTrace();
            /* 트랜잭션 롤백 */
            transaction.rollback();

        } finally {
            /* 사용이 완료되면 close 처리 해주어야 함. */
            entityManager.close();
        }

        /* close 필요. close 후 사용했던 자원들을 반납. */
        entityManagerFactory.close();
    }
}
