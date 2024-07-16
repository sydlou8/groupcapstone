package com.pluralsight.controllers;


import com.pluralsight.SandwichModels.Meat;
import com.pluralsight.data.MeatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/meats")
public class MeatController
{
    private final MeatDao meatDao;

    @Autowired
    public MeatController(MeatDao meatDao)
    {
        this.meatDao = meatDao;
    }

    @GetMapping
    public ResponseEntity<List<Meat>> getAllMeats()
    {
        List<Meat> meats = meatDao.getAllMeats();

        return ResponseEntity.ok(meats);
    }

    @GetMapping("/{meatId}")
    public ResponseEntity<Meat> getMeatById(@PathVariable int meatId)
    {
        Meat meat = meatDao.getById(meatId);
        if (meat != null)
        {
            return ResponseEntity.ok(meat);
        } else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Meat> createMeat(@RequestBody Meat meat)
    {
        Meat createdMeat = meatDao.create(meat);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdMeat);
    }

    @PutMapping("/{meatId}")
    public ResponseEntity<Void> updateMeat(@PathVariable int meatId, @RequestBody Meat meat)
    {
        meatDao.update(meatId, meat);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{meatId}")
    public ResponseEntity<Void> deleteMeat(@PathVariable int meatId)
    {
        meatDao.delete(meatId);

        return ResponseEntity.noContent().build();
    }

}

