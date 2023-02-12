package com.boursinos.hrplatform.model.controller.overtime.response;

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
public class OvertimeResponse {

    private String overtimeId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date overtimeDay;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Double overtime;

    private Date createdAt;

    private Date updatedAt;

}
