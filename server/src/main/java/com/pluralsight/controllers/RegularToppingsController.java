package com.pluralsight.controllers;

import com.pluralsight.SandwichModels.RegularToppings;
import com.pluralsight.data.mysql.MyRegularToppingsDao;
import com.pluralsight.models.toppings.RegularTopping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/regular_toppings")
@CrossOrigin
public class RegularToppingsController {

    private MyRegularToppingsDao regularToppingsDao = null;

    @Autowired
    public RegularToppingsController(MyRegularToppingsDao regularToppingsDao)
    {
        this.regularToppingsDao = regularToppingsDao;
    }

    @GetMapping("")
    public List<RegularToppings> getAll()
    {
        return regularToppingsDao.getAllRegularToppings();
    }

    @GetMapping("/{id}")
    public RegularToppings getRegularToppingsById(@PathVariable int id) {

        RegularToppings regularTopping = regularToppingsDao.getById(id);

        if (regularTopping == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return regularToppingsDao.getById(id);
    }

}
