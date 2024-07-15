package com.pluralsight.controllers;

import com.pluralsight.data.mysql.MySaucesDao;
import com.pluralsight.models.toppings.Sauces;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return saucesDao.getById(id);
    }

}
