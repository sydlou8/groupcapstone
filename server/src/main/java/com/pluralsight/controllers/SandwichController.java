package com.pluralsight.controllers;

import com.pluralsight.SandwichModels.Sandwich;
import com.pluralsight.data.SandwichDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/sandwiches")
public class SandwichController
{
    private final SandwichDao sandwichDao;

    @Autowired
    public SandwichController(SandwichDao sandwichDao)
    {
        this.sandwichDao = sandwichDao;
    }

    @GetMapping
    public ResponseEntity<List<Sandwich>> getAllSandwiches()
    {
        List<Sandwich> sandwiches = sandwichDao.getAllSandwiches();

        return ResponseEntity.ok(sandwiches);
    }

    @GetMapping("/{sandwichId}")
    public ResponseEntity<Sandwich> getSandwichById(@PathVariable int sandwichId)
    {
        Sandwich sandwich = sandwichDao.getById(sandwichId);

        if (sandwich != null)
        {
            return ResponseEntity.ok(sandwich);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

}
