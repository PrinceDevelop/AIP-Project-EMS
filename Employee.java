package DAO;

import javax.validation.constraints.*; // Bean Validation
public class Employee {

    private int id;
    
    @NotNull(message = "Name cannot be null")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    private String name;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Job Title cannot be null")
    @Size(min = 1, max = 50, message = "Job Title must be between 1 and 50 characters")
    private String jobTitle;

    @Min(value = 0, message = "Experience must be greater than or equal to 0")
    private int experience;

    @Min(value = 0, message = "Salary must be greater than or equal to 0")
    private double salary;

    @NotNull(message = "Applied Date cannot be null")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Applied Date must be in YYYY-MM-DD format")
    private String appliedDate;

    // No-arg constructor required for frameworks like JSP, Hibernate, etc.
    public Employee() {
    }

    // Parameterized Constructor with ID
    public Employee(int id, String name, String email, String jobTitle, int experience, double salary, String appliedDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.jobTitle = jobTitle;
        this.experience = experience;
        this.salary = salary;
        this.appliedDate = appliedDate;
    }

    // Parameterized Constructor without ID (common for adding new employees)
    public Employee(String name, String email, String jobTitle, int experience, double salary, String appliedDate) {
        this.name = name;
        this.email = email;
        this.jobTitle = jobTitle;
        this.experience = experience;
        this.salary = salary;
        this.appliedDate = appliedDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(String appliedDate) {
        this.appliedDate = appliedDate;
    }
}