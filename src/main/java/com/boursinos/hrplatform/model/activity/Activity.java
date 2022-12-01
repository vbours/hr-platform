package com.boursinos.hrplatform.model.activity;

import com.boursinos.hrplatform.model.employee.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Table
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
@Entity
public class Activity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "overtime_id")
    private String activityId;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @ToString.Exclude
    private Employee employee;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(name = "day")
    private int day;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(name = "month")
    private int month;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(name = "year")
    private int year;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(name = "working_hours")
    private double workingHours;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(name = "is_working")
    private boolean isWorking;


}
