package com.pluralsight.application;

import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
public class DeliController {
    private final DeliApp deliApp;
    private final DeliDao deliDao;

    public DeliController(DeliApp deliApp, DeliDao deliDao) {
        this.deliApp = deliApp;
        this.deliDao = deliDao;
    }

    @GetMapping("/run")
    public void runDeliApp() {
        deliApp.run();
    }


    @GetMapping("/delis")
    public List<Deli> getAllDelis() {
        return deliDao.getAllDelis();
    }

    @GetMapping("/delis/{id}")
    public Deli getDeliById(@PathVariable int id) {
        return deliDao.getDeliById(id);
    }

    @PostMapping("/delis")
    public void addDeli(@RequestBody Deli deli) {
        deliDao.addDeli(deli);
    }

    @PutMapping("/delis/{id}")
    public void updateDeli(@PathVariable int id, @RequestBody Deli deli) {
        deliDao.updateDeli(deli);
    }

    @DeleteMapping("/delis/{id}")
    public void deleteDeli(@PathVariable int id) {
        deliDao.deleteDeli(id);
    }
}