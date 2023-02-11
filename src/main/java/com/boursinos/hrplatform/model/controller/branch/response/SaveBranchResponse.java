package com.boursinos.hrplatform.model.controller.branch.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@JsonFormat
@NoArgsConstructor
@ToString
public class SaveBranchResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String branchId;

    public SaveBranchResponse(String branchId) {
        this.branchId = branchId;
    }
}
