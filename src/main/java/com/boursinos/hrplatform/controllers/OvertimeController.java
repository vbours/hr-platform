package com.boursinos.hrplatform.controllers;

import com.boursinos.hrplatform.model.entity.overtime.Overtime;
import com.boursinos.hrplatform.service.overtime.OvertimeService;
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
public class OvertimeController {

    static final Logger logger = Logger.getLogger(OvertimeController.class);

    @Autowired
    OvertimeService overtimeService;

    /**
     * This endpoint return all the overtime request.
     *
     * @return overtimes (List<Overtime>)
     */
    @GetMapping(value = "/overtime", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<Overtime>> getAllOvertimes() {
        logger.info("Get all overtimes request");
        List<Overtime> overtimes = overtimeService.getAllOvertimes();
        return new ResponseEntity<>(overtimes,HttpStatus.OK);
    }

    /**
     * This endpoint return all the overtimes request per employee.
     *
     * @return overtimes (List<Overtime>)
     */
    @GetMapping(value = "/employee/{employee_id}/overtime", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<Overtime>> getAllOvertimeByEmployee(@RequestParam String employeeId) {
        logger.info(String.format("Get all overtimes request for employee : %s" , employeeId));
        List<Overtime> overtimes = overtimeService.getAllOvertimesByEmployee(employeeId);
        return new ResponseEntity<>(overtimes,HttpStatus.OK);
    }

    /**
     * This endpoint return a specific overtime for a specific employee given an id.
     *
     * @param overtimeId the id of the overtime
     * @return overtime
     */
    @GetMapping(value = "/overtime/{overtime_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Optional<Overtime>> getOvertime(@RequestParam String overtimeId) {
        logger.info(String.format("Get overtime request - id : %s ", overtimeId));
        Optional<Overtime> overtime = overtimeService.getOvertime(overtimeId);
        return new ResponseEntity<>(overtime,HttpStatus.OK);
    }


    /**
     * This endpoint saves overtime data to the db.
     *
     * @param overtime request class for saving data
     * @param employeeId the id of the employee
     * @return id (str)
     */
    @PostMapping(value = "/employee/{employee_id}/overtime", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<String> saveOvertime(
            @RequestBody Overtime overtime, @RequestParam String employeeId) {
        logger.info(String.format("Save overtime request : %s, for employee: %s " , overtime.toString(), employeeId));
        String id = overtimeService.saveOvertime(overtime, employeeId);
        return new ResponseEntity<>(id,HttpStatus.CREATED);
    }

    /**
     * This endpoint updates overtime data to the db.
     *
     * @param overtime request class for saving data
     * @param overtimeId the specific id for the overtime
     * @param employeeId the id of the employee
     * @id the id of the overtime
     * @return overtime (Overtime)
     */
    @PutMapping(value = "/employee/{employee_id}/overtime/{overtime_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Overtime> updateOvertime(
            @RequestBody Overtime overtime, @RequestParam String employeeId, @RequestParam String overtimeId) {
        logger.info(String.format("Update overtime request : %s, for overtime_id : %s, for employee : %s" , overtime.toString(), overtimeId, employeeId));
        Overtime updatedOvertime = overtimeService.updateOvertime(overtimeId, overtime);
        return new ResponseEntity<>(updatedOvertime,HttpStatus.CREATED);
    }

    /**
     * This endpoint deletes overtime data from the db.
     *
     * @param overtimeId id of the overtime in the db that we want to delete
     */
    @DeleteMapping(value = "/overtime/{overtime_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<String> deleteOvertime(@RequestParam String overtimeId){
        logger.info(String.format("Delete overtime request id : %s" , overtimeId));
        overtimeService.deleteOvertime(overtimeId);
        return new ResponseEntity<>(overtimeId,HttpStatus.OK);
    }
}
