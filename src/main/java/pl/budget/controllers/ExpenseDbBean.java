
package pl.budget.controllers;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.transaction.UserTransaction;
import pl.budget.dao.ExpenseJpaController;
import pl.budget.entity.ExpenseEntity;
import pl.budget.to.ExpenseTO;

/**
 *
 * @author pawel
 */
@Named(value = "expenseDbBean")
@SessionScoped
public class ExpenseDbBean implements Serializable {

    @PersistenceContext(unitName = "ProjektBudzetPU")
    private EntityManager em;

    @Resource
    private UserTransaction utx;

    private List<ExpenseTO> expenseToList = new ArrayList<>();
    /**
     * Creates a new instance of ExpenseDbBean
     */
    public ExpenseDbBean() {
    }
    
    //Odczyt
    @PostConstruct
    public void refreshData() {
        try {
            ExpenseJpaController dao = new ExpenseJpaController(utx, em);
            List<ExpenseEntity> entities = dao.findExpenseEntities();
            expenseToList.clear();
            if (entities != null) {
                for (ExpenseEntity e : entities) {
                    expenseToList.add(new ExpenseTO(
                            e.getId(),
                            e.getNumer(),
                            e.getTytul(),
                            e.getOpis(),
                            e.getKwota(),
                            false
                    ));
                }
            }
        } catch (Exception ex) {
            sendJSFErrorMessage(ex);
        }
    }

    public void visibleChange(ExpenseTO item) {
        int index = expenseToList.indexOf(item);
        ExpenseJpaController dao = new ExpenseJpaController(utx, em);
        try {
            dao.edit(new ExpenseEntity(
                    item.getId(),
                    item.getNumer(),
                    item.getTytul(),
                    item.getOpis(),
                    item.getKwota()
            ));
            if (index >= 0) {
                expenseToList.set(index, item);
            }
        } catch (Exception ex) {
            sendJSFErrorMessage(ex);
        }
    }

    public void addRow() {
        ExpenseJpaController dao = new ExpenseJpaController(utx, em);
        Long id = System.currentTimeMillis();
        ExpenseEntity newEntity = new ExpenseEntity(id, "FV/NOWA", "Nowy wydatek", "Opis z formularza", 0.0);
        try {
            dao.create(newEntity);
            refreshData();
        } catch (Exception ex) {
            sendJSFErrorMessage(ex);
        }
    }

    public void deleteRow(ExpenseTO item) {
        ExpenseJpaController dao = new ExpenseJpaController(utx, em);
        try {
            dao.destroy(item.getId());
            refreshData();
        } catch (Exception ex) {
            sendJSFErrorMessage(ex);
        }
    }

    // Obsługa błędów
    public void sendJSFErrorMessage(Exception ex) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        servletContext.log(servletContext.getContextPath() + ": " + ex.toString());
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Blad bazy danych: " + ex.toString(), null));
    }

    public List<ExpenseTO> getExpenseToList() {
        return expenseToList;
    }

    public void setExpenseToList(List<ExpenseTO> expenseToList) {
        this.expenseToList = expenseToList;
    }
}
