package com.boursinos.hrplatform.model.controller.overtime.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SaveOvertimeResponse {

    private String overtimeId;

    public SaveOvertimeResponse(String overtimeId) {
        this.overtimeId = overtimeId;
    }
}
