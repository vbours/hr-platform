package com.boursinos.hrplatform.model.branch;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@JsonFormat
@NoArgsConstructor
@ToString
@Entity
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
@Table(name = "Branch")
public class Branch {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "branch_id")
    private String branchId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "address")
    private String address;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "city")
    private String city;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "country")
    private String country;

}
