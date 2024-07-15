package com.pluralsight.controllers;

import com.pluralsight.data.SaucesDao;
import com.pluralsight.data.mysql.MySaucesDao;
import com.pluralsight.models.toppings.Sauces;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/sauces")
public class SaucesController {

    private MySaucesDao saucesDao;

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
