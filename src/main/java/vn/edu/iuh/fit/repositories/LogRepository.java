package vn.edu.iuh.fit.repositories;


import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import jakarta.transaction.Transaction;
import vn.edu.iuh.fit.entities.Log;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

//@ApplicationScoped
//@Transactional

@Stateless
//@TransactionAttribute(TransactionAttributeType.NEVER)
public class LogRepository {
    private EntityManager em;

    public LogRepository() {
        em = Persistence.createEntityManagerFactory("test").createEntityManager();
    }

    public List<Log> findAll() {
        TypedQuery<Log> q = em.createQuery("select l from Log l", Log.class);
        return q.getResultList();
    }

    public List<Log> findLogByUser(String account_id) {
        TypedQuery<Log> q = em.createQuery("select l from Log l where l.account_id=?1", Log.class);
        q.setParameter(1, account_id);
        return q.getResultList();
    }

    public Log findLogByID(String account_id){
        TypedQuery<Log> q = em.createQuery("select l from Log l where l.account_id=?1",Log.class);
        q.setParameter(1,account_id);
        return q.getSingleResult();
    }

    public List<Log> findLogInTime(Date begin, Date end) {
        TypedQuery<Log> q = em.createQuery("select l from Log l where l.login_time>=?1 and l.login_time<=?2", Log.class);
        q.setParameter(1, begin);
        q.setParameter(2, end);
        return q.getResultList();
    }

    public void insert(Log log) {
        em.persist(log);
    }

    public void updateLogin(Log log) {
        log.setLogin_time(new Date(System.currentTimeMillis()));
        //new Date(Calendar.getInstance().getTimeInMillis());
        em.merge(log);
    }

    public void updatelogout(Log log){
        log.setLogout_time(new Date(System.currentTimeMillis()));
        em.merge(log);
    }

}
