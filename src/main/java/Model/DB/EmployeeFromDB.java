package Model.DB;

import java.sql.Timestamp;
import java.util.Objects;

public class EmployeeFromDB {
    private int id;
    private boolean is_active;
    private Timestamp create_timestamp;
    private Timestamp change_timestamp;
    private String first_name;
    private String last_name;
    private String middle_name;
    private String phone;
    private String email;
    private String avatar_url;
    private int company_id;
    private Timestamp birthdate;

    public EmployeeFromDB() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public Timestamp getCreate_timestamp() {
        return create_timestamp;
    }

    public void setCreate_timestamp(Timestamp create_timestamp) {
        this.create_timestamp = create_timestamp;
    }

    public Timestamp getChange_timestamp() {
        return change_timestamp;
    }

    public void setChange_timestamp(Timestamp change_timestamp) {
        this.change_timestamp = change_timestamp;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public Timestamp getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Timestamp birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeFromDB employee)) return false;
        return getId() == employee.getId() && getIs_active() == employee.getIs_active() && getCompany_id() == employee.getCompany_id() && Objects.equals(getCreate_timestamp(), employee.getCreate_timestamp()) && Objects.equals(getChange_timestamp(), employee.getChange_timestamp()) && Objects.equals(getFirst_name(), employee.getFirst_name()) && Objects.equals(getLast_name(), employee.getLast_name()) && Objects.equals(getMiddle_name(), employee.getMiddle_name()) && Objects.equals(getPhone(), employee.getPhone()) && Objects.equals(getEmail(), employee.getEmail()) && Objects.equals(getAvatar_url(), employee.getAvatar_url()) && Objects.equals(getBirthdate(), employee.getBirthdate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIs_active(), getCreate_timestamp(), getChange_timestamp(), getFirst_name(), getLast_name(), getMiddle_name(), getPhone(), getEmail(), getAvatar_url(), getCompany_id(), getBirthdate());
    }
}
