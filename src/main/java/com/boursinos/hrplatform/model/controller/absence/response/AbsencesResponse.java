package com.boursinos.hrplatform.model.controller.absence.response;

import com.boursinos.hrplatform.model.entity.absence.Absence;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AbsencesResponse {
    private List<Absence> absenceList;

    public AbsencesResponse(List<Absence> absenceList) {
        this.absenceList = absenceList;
    }
}
