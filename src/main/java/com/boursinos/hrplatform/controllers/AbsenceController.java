package com.boursinos.hrplatform.controllers;

import com.boursinos.hrplatform.model.absence.Absence;
import com.boursinos.hrplatform.service.AbsenceService;
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
     * This endpoint return all the absence request per employee.
     *
     * @return absences (List<Absences></>)
     */
    @GetMapping(value = "/employee/{employee_id}/absence", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<Absence>> getAllAbsences(@RequestParam String employeeId) {
        logger.info(String.format("Get all absences request for employee : %s" , employeeId));
        List<Absence> absences = absenceService.getAllAbsences(employeeId);
        return new ResponseEntity<>(absences,HttpStatus.OK);
    }

    /**
     * This endpoint return a specific absence for a specific employee given an id.
     *
     * @param employee_id the id of the employee
     * @param id the id of the absence
     * @return absence
     */
    @GetMapping(value = "/employee/{employee_id}/absence/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Optional<Absence>> getAbsence(@RequestParam String employee_id, @RequestParam String id) {
        logger.info(String.format("Get absence request - id : %s , for employee : %s ", id, employee_id));
        Optional<Absence> absence = absenceService.getAbsence(employee_id, id);
        return new ResponseEntity<>(absence,HttpStatus.OK);
    }


    /**
     * This endpoint saves absence data to the db.
     *
     * @param absence request class for saving data
     * @param employee_id the id of the employee
     * @return id (str)
     */
    @PostMapping(value = "/employee/{employee_id}/absence", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<String> saveAbsence(
            @RequestBody Absence absence, @RequestParam String employee_id) {
        logger.info(String.format("Save Absence request : %s, for employee: %s " , absence.toString(), employee_id));
        String id = absenceService.saveAbsence(absence, employee_id);
        return new ResponseEntity<>(id,HttpStatus.CREATED);
    }

    /**
     * This endpoint updates absence data to the db.
     *
     * @param absence request class for saving data
     * @param employee_id the id of the employee
     * @id the id of the absence
     * @return absence (Absence)
     */
    @PutMapping(value = "/employee/{employee_id}/absence/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Absence> updateAbsence(
            @RequestBody Absence absence, @RequestParam String employee_id, @RequestParam String id) {
        logger.info(String.format("Update absence request : %s, for absence_id : %s, for employee : %s" , absence.toString(), id, employee_id));
        Absence updatedAbsence = absenceService.updateAbsence(employee_id, id, absence);
        return new ResponseEntity<>(updatedAbsence,HttpStatus.CREATED);
    }

    /**
     * This endpoint deletes absence data from the db.
     *
     * @param id id of the absence in the db that we want to delete
     */
    @DeleteMapping(value = "/employee/{employee_id}/absence/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<String> deleteAbsence(
            @RequestParam String employee_id, @RequestParam String id){
        logger.info(String.format("Delete absence request id : %s , for employee : %S " , id, employee_id));
        absenceService.deleteAbsence(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }
}
