package id.dondon.ppmt.domain;

import id.dondon.ppmt.constant.ProjectField;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = ProjectField.PROJECT_NAME)
    @NotBlank(message = "Project name is required")
    private String projectName;

    @NotBlank(message = "Project Identifier is required")
    @Size(min =4, max=5, message = "Please use 4 to 5 characters")
    @Column(name = ProjectField.PROJECT_IDENTIFIER, unique = true, updatable = false)
    private String projectIdentifier;

    @Column(name = ProjectField.DESCRIPTION)
    @NotBlank(message = "Project description is requiredI")
    private String description;

    @Column(name = ProjectField.START_DATE)
    private Date startDate;

    @Column(name = ProjectField.END_DATE)
    private Date endDate;

    @Column(name = ProjectField.CREATED_AT)
    private Date createdAt;

    @Column(name = ProjectField.UPDATED_AT)
    private Date updatedAt;

    public Project() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

}
