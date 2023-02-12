package com.boursinos.hrplatform.model.controller.overtime.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class OvertimeRequest {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date overtimeDay;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Double overtime;
}
