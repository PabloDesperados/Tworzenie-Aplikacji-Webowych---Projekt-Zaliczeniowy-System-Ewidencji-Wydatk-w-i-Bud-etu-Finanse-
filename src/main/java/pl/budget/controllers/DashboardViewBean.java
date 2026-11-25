
package pl.budget.controllers;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author pawel
 */
@Named(value = "dashboardViewBean")
@ViewScoped
public class DashboardViewBean implements Serializable {

    @Inject
    private SessionController sessionController;

    private int viewRefreshCounter = 0;
    private boolean showLineChart = true;
    private boolean showBarChart = true;
    /**
     * Creates a new instance of DashboardViewBean
     */
    public DashboardViewBean() {
    }
    
    @PostConstruct
    public void init() {
        viewRefreshCounter = 1;
    }

    public void incrementCounter() {
        viewRefreshCounter++;
    }

    public String getCombinedSummary() {
        return "Uzytkownik: " + sessionController.getLoggedUserName()
                + " | Motyw: " + sessionController.getCurrentTheme()
                + " | Odswiezenia widoku: " + viewRefreshCounter;
    }

    public int getViewRefreshCounter() {
        return viewRefreshCounter;
    }

    public void setViewRefreshCounter(int viewRefreshCounter) {
        this.viewRefreshCounter = viewRefreshCounter;
    }

    public boolean isShowLineChart() {
        return showLineChart;
    }

    public void setShowLineChart(boolean showLineChart) {
        this.showLineChart = showLineChart;
    }

    public boolean isShowBarChart() {
        return showBarChart;
    }

    public void setShowBarChart(boolean showBarChart) {
        this.showBarChart = showBarChart;
    }
}
