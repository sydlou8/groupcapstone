package com.pluralsight.controllers;


import com.pluralsight.SandwichModels.Bread;
import com.pluralsight.data.BreadDao;
import com.pluralsight.data.mysql.MyBreadDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/breads")
public class BreadController
{
    private final BreadDao breadDao;

    @Autowired
    public BreadController(BreadDao breadDao)
    {
        this.breadDao = breadDao;
    }

    @GetMapping
    public ResponseEntity<List<Bread>> getAllBreads()
    {
        List<Bread> breads = breadDao.getAllBreads();
        return ResponseEntity.ok(breads);
    }

    @GetMapping("/{breadId}")
    public ResponseEntity<Bread> getBreadById(@PathVariable("breadId") int breadId)
    {
        Bread bread = breadDao.getById(breadId);
        if (bread != null)
        {
            return ResponseEntity.ok(bread);
        } else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Bread>createBread(@RequestBody Bread bread)
    {
        Bread createdBread = breadDao.create(bread);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdBread);
    }

    @PutMapping("/{breadId}")
    public ResponseEntity<Void> updateBread(@PathVariable("breadId") int breadId, @RequestBody Bread bread)
    {
        breadDao.update(breadId, bread);

        return ResponseEntity.noContent().build();
    }

}
