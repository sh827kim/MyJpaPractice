package jpabasic.reserve.domain.main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpabasic.reserve.domain.User;

/***
 * Update 는 별도 update 메서드 없이 Entity의 값을 변경하는 것 만으로 Update 처리가 된다.
 */
public class UserUpdateMain {
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
                var newName = "이름" + (System.currentTimeMillis()%100);
                user.changeName(newName);
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
