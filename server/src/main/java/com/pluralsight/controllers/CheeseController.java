package com.pluralsight.controllers;


import com.pluralsight.SandwichModels.Cheese;
import com.pluralsight.data.CheeseDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cheeses")
@CrossOrigin
public class CheeseController
{
    private final CheeseDao cheeseDao;

    public CheeseController(CheeseDao cheeseDao)
    {
        this.cheeseDao = cheeseDao;
    }

    @GetMapping
    public ResponseEntity<List<Cheese>> getAllCheeses()
    {
        List<Cheese> cheeses = cheeseDao.getAllCheeses();

        return ResponseEntity.ok(cheeses);
    }

    @GetMapping("/{cheeseId}")
    public ResponseEntity<Cheese> getCheeseById(@PathVariable int cheeseId)
    {
        Cheese cheese = cheeseDao.getById(cheeseId);
        if (cheese != null)
        {
            return ResponseEntity.ok(cheese);
        } else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cheese> createCheese(@RequestBody Cheese cheese)
    {
        Cheese createdCheese = cheeseDao.create(cheese);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdCheese);
    }

    @PutMapping("/{cheeseId}")
    public ResponseEntity<Void> updateCheese(@PathVariable int cheeseId, @RequestBody Cheese cheese)
    {
        cheeseDao.update(cheeseId, cheese);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cheeseId}")
    public ResponseEntity<Void> deleteCheese(@PathVariable int cheeseId)
    {
        cheeseDao.delete(cheeseId);

        return ResponseEntity.noContent().build();
    }
}

