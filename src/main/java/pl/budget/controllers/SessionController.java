
package pl.budget.controllers;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import pl.budget.common.MenuItem;

/**
 *
 * @author pawel
 */
@Named(value = "sessionController")
@SessionScoped
public class SessionController implements Serializable {

    // Punkt 5: Wstrzykniecie komponentu o zasiegu aplikacji do komponentu o zasiegu sesji
    @Inject
    private ApplicationController applicationControllerReference;

    private List<String> themes;
    private String currentTheme = "nova-light";
    private ArrayList<MenuItem> menuItemList = new ArrayList<>();
    private String sessionNote = "Aktywna sesja uzytkownika";
    /**
     * Creates a new instance of SessionController
     */
    public SessionController() {
    }
    
    @PostConstruct
    public void init() {
        // Rejestracja nowej sesji w komponencie ApplicationScoped
        if (applicationControllerReference != null) {
            applicationControllerReference.incrementSessions();
        }

        // Inicjalizacja motywow PrimeFaces (Zadanie 8, pkt 3g)
        themes = new ArrayList<>();
        themes.add("nova-light");
        themes.add("nova-dark");
        themes.add("nova-colored");
        themes.add("luna-blue");
        themes.add("luna-amber");
        themes.add("luna-green");
        themes.add("saga");
        themes.add("vela");
        themes.add("arya");

        // Inicjalizacja pozycji menu z uzyciem regul nawigacji z faces-config.xml (Zadanie 7, pkt 3b)
        menuItemList.add(new MenuItem("Pulpit i Wykresy", "goDashboard"));
        menuItemList.add(new MenuItem("Budzet Sesyjny (AJAX)", "goSessionBudget"));
        menuItemList.add(new MenuItem("Rejestr Wydatkow (Baza H2)", "goDbExpenses"));
    }

    // Metoda wspolpracy miedzy komponentami (Zadanie 2, pkt 6d)
    public String getDataFromReferenceComponent() {
        return applicationControllerReference.getAppVersion() + " | Waluta: " + applicationControllerReference.getCurrency();
    }

    // Przyklad nawigacji dynamicznej z kontrolera (Zadanie 2, pkt 9d)
    public String navigateDynamicToDb() {
        return "goDbExpenses";
    }

    // Jawne zakonczenie sesji i wylogowanie (Zadanie 3, pkt 7a)
    public String logout() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        return "/logoutWindow.xhtml?faces-redirect=true";
    }

    // Odczyt nazwy zalogowanego uzytkownika z TomEE
    public String getLoggedUserName() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        return (ec.getRemoteUser() != null) ? ec.getRemoteUser() : "Gosc";
    }

    public ApplicationController getApplicationControllerReference() {
        return applicationControllerReference;
    }

    public void setApplicationControllerReference(ApplicationController applicationControllerReference) {
        this.applicationControllerReference = applicationControllerReference;
    }

    public List<String> getThemes() {
        return themes;
    }

    public void setThemes(List<String> themes) {
        this.themes = themes;
    }

    public String getCurrentTheme() {
        return currentTheme;
    }

    public void setCurrentTheme(String currentTheme) {
        this.currentTheme = currentTheme;
    }

    public ArrayList<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    public void setMenuItemList(ArrayList<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }

    public String getSessionNote() {
        return sessionNote;
    }

    public void setSessionNote(String sessionNote) {
        this.sessionNote = sessionNote;
    }
}
