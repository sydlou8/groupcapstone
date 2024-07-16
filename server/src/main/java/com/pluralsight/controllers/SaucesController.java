package com.pluralsight.controllers;

import com.pluralsight.data.SaucesDao;
import com.pluralsight.data.mysql.MySaucesDao;
import com.pluralsight.models.toppings.Sauces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/sauces")
@CrossOrigin
public class SaucesController {

    private MySaucesDao saucesDao;

    @Autowired
    public SaucesController(MySaucesDao saucesDao) {
        this.saucesDao = saucesDao;
    }

    @GetMapping
    public List<Sauces> getAllSauces() {
        return saucesDao.getAllSauces();
    }

    @GetMapping("/{id}")
    public Sauces getSauceById(@PathVariable int id) {
        Sauces sauces = saucesDao.getById(id);

        if (sauces == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return saucesDao.getById(id);
    }

}
