
package pl.budget.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author pawel
 */
@Entity
@Table(name = "T_WYDATKI")
@NamedQueries({
    @NamedQuery(name = "ExpenseEntity.findAll", query = "SELECT e FROM ExpenseEntity e")
})
public class ExpenseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NUMER")
    private String numer;

    @Column(name = "TYTUL")
    private String tytul;

    @Column(name = "OPIS")
    private String opis;

    @Column(name = "KWOTA")
    private Double kwota;
    
    public ExpenseEntity() {
    }

    public ExpenseEntity(Long id, String numer, String tytul, String opis, Double kwota) {
        this.id = id;
        this.numer = numer;
        this.tytul = tytul;
        this.opis = opis;
        this.kwota = kwota;
    }

    public String getNumer() {
        return numer;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Double getKwota() {
        return kwota;
    }

    public void setKwota(Double kwota) {
        this.kwota = kwota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExpenseEntity)) {
            return false;
        }
        ExpenseEntity other = (ExpenseEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.budget.entity.ExpenseEntity[ id=" + id + " ]";
    }
    
}
