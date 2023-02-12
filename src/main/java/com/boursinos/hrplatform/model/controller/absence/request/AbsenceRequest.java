package com.boursinos.hrplatform.model.controller.absence.request;

import com.boursinos.hrplatform.model.entity.absence.AbsenceStatus;
import com.boursinos.hrplatform.model.entity.absence.AbsenceType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AbsenceRequest {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date startAt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date endAt;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int requestedDays;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AbsenceType absenceType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AbsenceStatus absenceStatus;

}
