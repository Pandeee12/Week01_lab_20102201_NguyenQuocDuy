package vn.edu.iuh.fit.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.entities.GrantAccess;
import vn.edu.iuh.fit.entities.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoleRepository {
    private final Logger logger = Logger.getLogger(AccountRepository.class.getName());
    private EntityManager em;
    private EntityTransaction trans;
//    private AccountRepository accountRepository;
//    private GrantAccessRepository grantAccessRepository;

    public RoleRepository() {
        em = Persistence.createEntityManagerFactory("test").createEntityManager();
        trans = em.getTransaction();
    }

    public void addRole(Role role){
        try {
            trans.begin();
            em.persist(role);
            //when insert new role, autoatically grant access to all account with false
            AccountRepository accountRepository=new AccountRepository();
            GrantAccessRepository grantAccessRepository = new GrantAccessRepository();
            List<Account>accounts = accountRepository.getAllAccounts();
            accounts.forEach(account -> {
                GrantAccess grantAccess = new GrantAccess(account.getAccount_id(), role.getRole_id(), false, "initialize");
                grantAccessRepository.insert(grantAccess);
            });

            trans.commit();
        } catch (Exception exception) {
            trans.rollback();
            logger.log(Level.SEVERE, exception.getMessage() + "\n" + exception.getCause());
        }
    }

    public void update(Role role){
        try {
            trans.begin();
            em.merge(role);
            trans.commit();
        } catch (Exception exception) {
            trans.rollback();
            logger.log(Level.SEVERE, exception.getMessage() + "\n" + exception.getCause());
        }
    }

    /**
     * Update status of role
     *
     * @param role_id: account id
     * @param status:    1-active;0-deactivate;-1 deleted
     */
    public boolean updateStatus(String role_id, int status) {
        try {
            trans.begin();
            Account acc = em.find(Account.class, role_id);
            if (acc != null)
                acc.setStatus(status);
            trans.commit();
            return true;
        } catch (Exception exception) {
            trans.rollback();
            logger.log(Level.SEVERE, exception.getMessage() + "\n" + exception.getCause());
        }
        return false;
    }

    public List<Role> getAllRoles() {
        TypedQuery<Role> q = em.createQuery("select r from Role r", Role.class);
        return q.getResultList();
    }

    public List<String> getRoleIdsfromGrantAccess(List<GrantAccess> grantAccesses){
        List<String> roleIDList = new ArrayList<>();
        for(GrantAccess grantAccess : grantAccesses){
            if(grantAccess.isGrant() == true){
                roleIDList.add(grantAccess.getRole_id());
            }
        }
        return roleIDList;
    }
}
