package com.boursinos.hrplatform.controllers;

import com.boursinos.hrplatform.model.entity.absence.Absence;
import com.boursinos.hrplatform.service.absence.AbsenceService;
import org.apache.log4j.Logger;
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
    @GetMapping(value = "/absence", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<Absence>> getAllAbsences() {
        logger.info("Get all absences request");
        List<Absence> absences = absenceService.getAllAbsences();
        return new ResponseEntity<>(absences,HttpStatus.OK);
    }

    /**
     * This endpoint return all the absence request per employee.
     *
     * @return absences (List<Absences>)
     */
    @GetMapping(value = "/employee/{employee_id}/absence", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<Absence>> getAllAbsencesByEmployee(@RequestParam String employeeId) {
        logger.info(String.format("Get all absences request for employee : %s" , employeeId));
        List<Absence> absences = absenceService.getAllAbsencesByEmployee(employeeId);
        return new ResponseEntity<>(absences,HttpStatus.OK);
    }

    /**
     * This endpoint return a specific absence for a specific employee given an id.
     *
     * @param absenceId the id of the absence
     * @return absence
     */
    @GetMapping(value = "/absence/{absence_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Optional<Absence>> getAbsence(@RequestParam String absenceId) {
        logger.info(String.format("Get absence request - id : %s ", absenceId));
        Optional<Absence> absence = absenceService.getAbsence(absenceId);
        return new ResponseEntity<>(absence,HttpStatus.OK);
    }


    /**
     * This endpoint saves absence data to the db.
     *
     * @param absence request class for saving data
     * @param employeeId the id of the employee
     * @return id (str)
     */
    @PostMapping(value = "/employee/{employee_id}/absence", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<String> saveAbsence(
            @RequestBody Absence absence, @RequestParam String employeeId) {
        logger.info(String.format("Save Absence request : %s, for employee: %s " , absence.toString(), employeeId));
        String id = absenceService.saveAbsence(absence, employeeId);
        return new ResponseEntity<>(id,HttpStatus.CREATED);
    }

    /**
     * This endpoint updates absence data to the db.
     *
     * @param absence request class for saving data
     * @param absenceId the specific id for the absence
     * @param employeeId the id of the employee
     * @id the id of the absence
     * @return absence (Absence)
     */
    @PutMapping(value = "/employee/{employee_id}/absence/{absence_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Absence> updateAbsence(
            @RequestBody Absence absence, @RequestParam String employeeId, @RequestParam String absenceId) {
        logger.info(String.format("Update absence request : %s, for absence_id : %s, for employee : %s" , absence.toString(), absenceId, employeeId));
        Absence updatedAbsence = absenceService.updateAbsence(employeeId, absenceId, absence);
        return new ResponseEntity<>(updatedAbsence,HttpStatus.CREATED);
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
