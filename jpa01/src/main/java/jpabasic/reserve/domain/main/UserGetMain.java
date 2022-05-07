package jpabasic.reserve.domain.main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpabasic.reserve.domain.User;


public class UserGetMain {
    public static void main(String[] args) {

        /* DB 연동을 위한 기반 생성 */
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpabegin");

        /* 실제 DB 연동 */
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        /* DB Transaction 처리 */
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            /* DB 객체 Read */
            User user = entityManager.find(User.class, "user@user.com");

            if(user == null) {
                System.out.println("No user");
            } else {
                System.out.printf("User exists : email=%s, name=%s, createDate%s\n",
                        user.getEmail(), user.getName(), user.getCreateDate());
            }

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();

        } finally {
            /* 사용이 완료되면 close 처리 해주어야 함. */
            entityManager.close();
        }

        /* close 필요. close 후 사용했던 자원들을 반납. */
        entityManagerFactory.close();
    }
}
