package vn.edu.iuh.fit.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import vn.edu.iuh.fit.entities.GrantAccess;
import vn.edu.iuh.fit.enums.GrantValue;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GrantAccessRepository {
    private final Logger logger = Logger.getLogger(AccountRepository.class.getName());
    private EntityManager em;
    private EntityTransaction trans;

    @Inject
    private AccountRepository accountRepository;
    @Inject
    private GrantAccessRepository grantAccessRepository;

    public GrantAccessRepository() {
        em = Persistence.createEntityManagerFactory("test").createEntityManager();
        trans = em.getTransaction();
    }

    public void insert(GrantAccess grantAccess) {
        try {
            trans.begin();
            em.persist(grantAccess);
            trans.commit();
        } catch (Exception exception) {
            trans.rollback();
            logger.log(Level.SEVERE, exception.getMessage() + "\n" + exception.getCause());
        }
    }

    public void grant(String account_id, String role_id, boolean allowed) {
        try {
            trans.begin();
            GrantAccess grantAccess = new GrantAccess(account_id, role_id, allowed, allowed?"enable":"disable");
            em.merge(grantAccess);
            trans.commit();
        } catch (Exception exception) {
            trans.rollback();
            logger.log(Level.SEVERE, exception.getMessage() + "\n" + exception.getCause());
        }
    }

    public List<GrantAccess> findByAccount(String account_id){
        TypedQuery<GrantAccess> q = em.createQuery("select g from GrantAccess g where g.account_id=?1", GrantAccess.class);
        q.setParameter(1,account_id);
        return q.getResultList();
    }
}
