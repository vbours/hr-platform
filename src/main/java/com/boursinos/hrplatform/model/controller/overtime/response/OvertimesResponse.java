package com.boursinos.hrplatform.model.controller.overtime.response;

import com.boursinos.hrplatform.model.entity.overtime.Overtime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OvertimesResponse {
    private List<Overtime> overtimeList;

    public OvertimesResponse(List<Overtime> overtimeList) {
        this.overtimeList = overtimeList;
    }
}
