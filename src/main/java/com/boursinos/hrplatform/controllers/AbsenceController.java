package com.boursinos.hrplatform.controllers;

import com.boursinos.hrplatform.model.controller.absence.request.AbsenceRequest;
import com.boursinos.hrplatform.model.controller.absence.response.AbsenceResponse;
import com.boursinos.hrplatform.model.controller.absence.response.AbsencesResponse;
import com.boursinos.hrplatform.model.controller.absence.response.SaveAbsenceResponse;
import com.boursinos.hrplatform.model.entity.absence.Absence;
import com.boursinos.hrplatform.model.entity.branch.Branch;
import com.boursinos.hrplatform.service.absence.AbsenceService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class AbsenceController {

    static final Logger logger = Logger.getLogger(AbsenceController.class);

    @Autowired
    AbsenceService absenceService;

    /**
     * This endpoint return all the absence request.
     *
     * @return absences (List<Absences>)
     */
    @GetMapping(value = "/absences", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<AbsencesResponse> getAllAbsences() {
        logger.info("Get all absences request");
        List<Absence> absences = absenceService.getAllAbsences();
        return new ResponseEntity<>(new AbsencesResponse(absences),HttpStatus.OK);
    }

    /**
     * This endpoint return all the absence request per employee.
     *
     * @return absences (List<Absences>)
     */
    @GetMapping(value = "/employee/{employee_id}/absence", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<AbsencesResponse> getAllAbsencesByEmployee(@RequestParam String employeeId) {
        logger.info(String.format("Get all absences request for employee : %s" , employeeId));
        List<Absence> absences = absenceService.getAllAbsencesByEmployee(employeeId);
        return new ResponseEntity<>(new AbsencesResponse(absences),HttpStatus.OK);
    }

    /**
     * This endpoint return a specific absence for a specific employee given an id.
     *
     * @param absenceId the id of the absence
     * @return absence
     */
    @GetMapping(value = "/absence/{absence_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<AbsenceResponse> getAbsence(@RequestParam String absenceId) {
        logger.info(String.format("Get absence request - id : %s ", absenceId));
        Optional<Absence> absence = absenceService.getAbsence(absenceId);
        ModelMapper modelMapper = new ModelMapper();
        AbsenceResponse absenceResponse = modelMapper.map(absence.get(), AbsenceResponse.class);
        return new ResponseEntity<>(absenceResponse,HttpStatus.OK);
    }


    /**
     * This endpoint saves absence data to the db.
     *
     * @param absenceRequest request class for saving data
     * @param employeeId the id of the employee
     * @return SaveAbsenceResponse
     */
    @PostMapping(value = "/employee/{employee_id}/absence", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<SaveAbsenceResponse> saveAbsence(
            @RequestBody AbsenceRequest absenceRequest, @RequestParam String employeeId) {
        logger.info(String.format("Save Absence request : %s, for employee: %s " , absenceRequest.toString(), employeeId));
        ModelMapper modelMapper = new ModelMapper();
        Absence absence = modelMapper.map(absenceRequest,Absence.class);
        String id = absenceService.saveAbsence(absence, employeeId);
        return new ResponseEntity<>(new SaveAbsenceResponse(id),HttpStatus.CREATED);
    }

    /**
     * This endpoint updates absence data to the db.
     *
     * @param absenceRequest request class for saving data
     * @param absenceId the specific id for the absence
     * @param employeeId the id of the employee
     * @id the id of the absence
     * @return absence (Absence)
     */
    @PutMapping(value = "/employee/{employee_id}/absence/{absence_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<AbsenceResponse> updateAbsence(
            @RequestBody AbsenceRequest absenceRequest, @RequestParam String employeeId, @RequestParam String absenceId) {
        logger.info(String.format("Update absence request : %s, for absence_id : %s, for employee : %s" , absenceRequest.toString(), absenceId, employeeId));
        ModelMapper modelMapper = new ModelMapper();
        Absence absence = modelMapper.map(absenceRequest,Absence.class);
        Absence updatedAbsence = absenceService.updateAbsence(employeeId, absenceId, absence);
        AbsenceResponse absenceResponse = modelMapper.map(updatedAbsence, AbsenceResponse.class);
        return new ResponseEntity<>(absenceResponse,HttpStatus.CREATED);
    }

    /**
     * This endpoint deletes absence data from the db.
     *
     * @param absenceId id of the absence in the db that we want to delete
     */
    @DeleteMapping(value = "/absence/{absence_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<String> deleteAbsence(@RequestParam String absenceId){
        logger.info(String.format("Delete absence request id : %s" , absenceId));
        absenceService.deleteAbsence(absenceId);
        return new ResponseEntity<>(absenceId,HttpStatus.OK);
    }
}
