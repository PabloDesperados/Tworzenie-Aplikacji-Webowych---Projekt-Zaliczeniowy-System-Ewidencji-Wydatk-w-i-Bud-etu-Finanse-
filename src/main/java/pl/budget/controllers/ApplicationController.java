
package pl.budget.controllers;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author pawel
 */
@Named(value = "applicationController")
@ApplicationScoped
public class ApplicationController {

    private String appVersion = "2026.1.0-RELEASE";
    private String currency = "PLN";
    private int totalSessionsCount = 0;
    /**
     * Creates a new instance of ApplicationController
     */
    public ApplicationController() {
    }
    
    public synchronized void incrementSessions() {
        totalSessionsCount++;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getTotalSessionsCount() {
        return totalSessionsCount;
    }

    public void setTotalSessionsCount(int totalSessionsCount) {
        this.totalSessionsCount = totalSessionsCount;
    }
}
