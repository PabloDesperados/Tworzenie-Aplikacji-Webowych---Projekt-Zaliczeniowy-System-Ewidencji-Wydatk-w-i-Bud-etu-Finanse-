
package pl.budget.controllers;

import java.io.Serializable;
/**
 *
 * @author pawel
 */
public class ApplicationControllerXML implements Serializable{

    private String student = "";
    private String projectTitle = "";
    /**
     * Creates a new instance of ApplicationControllerXML
     */
    public ApplicationControllerXML() {
    }
    
    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }
}
