package Model.API;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;


public class EmployeeEntity {
    private int id;
    private boolean is_active;
    private Timestamp create_timestamp;
    private Timestamp change_timestamp;
    private String first_name;
    private String last_name;
    private String middle_name;
    private String phone;
    private String email;
    private String avatar;

    private Date birthdate;
    private int company_id;

    public EmployeeEntity(int id, boolean is_active, String first_name, String last_name, String middle_name, String phone, String email, int company_id) {
        this.id = id;
        this.is_active = is_active;
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.phone = phone;
        this.email = email;
        this.company_id = company_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActiveParam() {
        return is_active;
    }

    public void setIsActive(boolean is_active) {
        this.is_active = is_active;
    }

    public Timestamp getTimestamp() {
        return create_timestamp;
    }

    public void createTimestamp(Timestamp create_timestamp) {
        this.create_timestamp = create_timestamp;
    }

    public Timestamp getChangedTimestamp(Timestamp timestamp) {
        return change_timestamp;
    }

    public void setTimestamp(Timestamp change_timestamp) {
        this.change_timestamp = change_timestamp;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getMiddleName() {
        return middle_name;
    }

    public void setMiddleName(String middle_name) {
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getCompanyId() {
        return company_id;
    }

    public void setCompanyId(int company_id) {
        this.company_id = company_id;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return id == that.id && is_active == that.is_active && company_id == that.company_id && Objects.equals(create_timestamp, that.create_timestamp) && Objects.equals(change_timestamp, that.change_timestamp) && Objects.equals(first_name, that.first_name) && Objects.equals(last_name, that.last_name) && Objects.equals(middle_name, that.middle_name) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email) && Objects.equals(avatar, that.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, is_active, create_timestamp, change_timestamp, first_name, last_name, middle_name, phone, email, avatar, company_id);
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "id=" + id +
                ", is_active=" + is_active +
                ", create_timestamp=" + create_timestamp +
                ", change_timestamp=" + change_timestamp +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", company_id=" + company_id +
                '}';
    }
}

