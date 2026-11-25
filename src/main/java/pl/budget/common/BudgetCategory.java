
package pl.budget.common;

import java.io.Serializable;
/**
 *
 * @author pawel
 */
public class BudgetCategory implements Serializable{
    
    private long id = -1;
    private String categoryName = "";
    private double amount = 0.0;
    private boolean edited = false;

    public BudgetCategory() {
    }
    
    public BudgetCategory(long id, String categoryName, double amount, boolean edited) {
        this.id = id;
        this.categoryName = categoryName;
        this.amount = amount;
        this.edited = edited;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }
}
