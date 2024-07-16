package com.pluralsight.controllers;


import com.pluralsight.SandwichModels.Drinks;
import com.pluralsight.data.DrinksDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drinks")
@CrossOrigin
public class DrinksController
{
    private final DrinksDao drinksDao;

    public DrinksController(DrinksDao drinksDao)
    {
        this.drinksDao = drinksDao;
    }

    @GetMapping
    public ResponseEntity<List<Drinks>> getAllDrinks()
    {
        List<Drinks> drinks = drinksDao.getAllDrinks();

        return ResponseEntity.ok(drinks);
    }

    @GetMapping("/{drinkId}")
    public ResponseEntity<Drinks> getDrinkById(@PathVariable int drinkId)
    {
        Drinks drinks = drinksDao.getById(drinkId);
        if(drinks != null)
        {
            return ResponseEntity.ok(drinks);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Drinks> createDrinks( @RequestBody Drinks drinks)
    {
        Drinks createdDrink = drinksDao.create(drinks);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdDrink);
    }

    @PutMapping("/{drinkId}")
    public ResponseEntity<Void> updateDrink(@PathVariable int drinkId, @RequestBody Drinks drink)
    {
        drinksDao.update(drinkId, drink);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{drinkId}")
    public ResponseEntity<Void> deleteDrink(@PathVariable int drinkId)
    {
        drinksDao.delete(drinkId);

        return ResponseEntity.noContent().build();
    }
}
