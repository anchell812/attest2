package Model.API;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeFromAPI {
    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private int companyId;
    private String email;
    @JsonProperty("avatar_url")
    private String url;
    private String phone;
    private String birthdate;
    private boolean isActive;

    public EmployeeFromAPI() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeFromAPI employee)) return false;
        return getId() == employee.getId() && getCompanyId() == employee.getCompanyId() && isActive == employee.isActive && Objects.equals(getFirstName(), employee.getFirstName()) && Objects.equals(getLastName(), employee.getLastName()) && Objects.equals(getMiddleName(), employee.getMiddleName()) && Objects.equals(getEmail(), employee.getEmail()) && Objects.equals(getUrl(), employee.getUrl()) && Objects.equals(getPhone(), employee.getPhone()) && Objects.equals(getBirthdate(), employee.getBirthdate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getMiddleName(), getCompanyId(), getEmail(), getUrl(), getPhone(), getBirthdate(), isActive);
    }
}
