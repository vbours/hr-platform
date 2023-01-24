package com.boursinos.hrplatform.model.absence;

import com.boursinos.hrplatform.model.employee.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
@Table
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
@Entity
@NoArgsConstructor
public class Absence {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "absence_id")
    private String absenceId;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @ToString.Exclude
    private Employee employee;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "start_at")
    private Date startAt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "end_at")
    private Date endAt;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @Column(name = "requested_days")
    private int requestedDays;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "absence_type")
    private AbsenceType absenceType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "absence_status")
    private AbsenceStatus absenceStatus;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public Absence(Date startAt, Date endAt, int requestedDays, AbsenceType absenceType, AbsenceStatus absenceStatus) {
        this.startAt = startAt;
        this.endAt = endAt;
        this.requestedDays = requestedDays;
        this.absenceType = absenceType;
        this.absenceStatus = absenceStatus;
    }

    public String getAbsenceId() {
        return absenceId;
    }

    public void setAbsenceId(String absenceId) {
        this.absenceId = absenceId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public int getRequestedDays() {
        return requestedDays;
    }

    public void setRequestedDays(int requestedDays) {
        this.requestedDays = requestedDays;
    }

    public AbsenceType getAbsenceType() {
        return absenceType;
    }

    public void setAbsenceType(AbsenceType absenceType) {
        this.absenceType = absenceType;
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

    public AbsenceStatus getAbsenceStatus() {
        return absenceStatus;
    }

    public void setAbsenceStatus(AbsenceStatus absenceStatus) {
        this.absenceStatus = absenceStatus;
    }
}
