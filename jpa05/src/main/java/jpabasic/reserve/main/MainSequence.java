package jpabasic.reserve.main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jpabasic.reserve.domain.ActivityLog;
import jpabasic.reserve.jpa.EMF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainSequence {
    private static Logger logger = LoggerFactory.getLogger(MainSequence.class);

    public static void main(String[] args) {
        EMF.init();
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            ActivityLog log = new ActivityLog("U01", "VISIT");
            logger.info("persist 실행 전");
            em.persist(log);
            logger.info("persist 실행 함");
            logger.info("생성한 식별자: {}", log.getId());
            logger.info("커밋하기 전");
            tx.commit();
            logger.info("커밋함");
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        EMF.close();
    }
}