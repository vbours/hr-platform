package com.boursinos.hrplatform.model.controller.absence.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@JsonFormat
@NoArgsConstructor
@ToString
public class SaveAbsenceResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String absenceId;

    public SaveAbsenceResponse(String absenceId) {
        this.absenceId = absenceId;
    }
}
