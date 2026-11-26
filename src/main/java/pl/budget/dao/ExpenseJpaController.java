
package pl.budget.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import pl.budget.entity.ExpenseEntity;
/**
 *
 * @author pawel
 */
public class ExpenseJpaController implements Serializable{
    
    private UserTransaction utx = null;
    private EntityManager em = null;
    
    public ExpenseJpaController(UserTransaction utx, EntityManager em) {
        this.utx = utx;
        this.em = em;
    }
    
    public void create(ExpenseEntity expense) throws Exception {
        try {
            utx.begin();
            em.joinTransaction();
            em.persist(expense);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new Exception("Blad wycofywania transakcji: " + re.getMessage(), re);
            }
            throw ex;
        }
    }

    public void edit(ExpenseEntity expense) throws Exception {
        try {
            utx.begin();
            em.joinTransaction();
            em.merge(expense);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new Exception("Blad wycofywania transakcji: " + re.getMessage(), re);
            }
            throw ex;
        }
    }

    public void destroy(Long id) throws Exception {
        try {
            utx.begin();
            em.joinTransaction();
            ExpenseEntity expense = em.getReference(ExpenseEntity.class, id);
            em.remove(expense);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new Exception("Blad wycofywania transakcji: " + re.getMessage(), re);
            }
            throw ex;
        }
    }

    @SuppressWarnings("unchecked")
    public List<ExpenseEntity> findExpenseEntities() {
        Query q = em.createNamedQuery("ExpenseEntity.findAll");
        return q.getResultList();
    }
}
