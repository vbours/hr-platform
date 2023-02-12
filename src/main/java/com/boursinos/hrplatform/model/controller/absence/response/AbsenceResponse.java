package com.boursinos.hrplatform.model.controller.absence.response;

import com.boursinos.hrplatform.model.entity.absence.AbsenceStatus;
import com.boursinos.hrplatform.model.entity.absence.AbsenceType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AbsenceResponse {
    private String absenceId;

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

    private Date createdAt;

    private Date updatedAt;
}
