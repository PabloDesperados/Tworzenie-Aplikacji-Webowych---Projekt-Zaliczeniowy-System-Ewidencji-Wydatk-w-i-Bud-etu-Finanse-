
package pl.budget.to;

import java.io.Serializable;

/**
 *
 * @author pawel
 */
public class ExpenseTO implements Serializable{
    
    private Long id = 0L;
    private String numer = "";
    private String tytul = "";
    private String opis = "";
    private Double kwota = 0.0;
    private boolean edited = false;
    
    public ExpenseTO() {
    }
    
    public ExpenseTO(Long id, String numer, String tytul, String opis, Double kwota, boolean edited) {
        this.id = id;
        this.numer = numer;
        this.tytul = tytul;
        this.opis = opis;
        this.kwota = kwota;
        this.edited = edited;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }
}
