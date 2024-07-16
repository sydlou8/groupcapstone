package com.pluralsight.controllers;


import com.pluralsight.SandwichModels.Sides;
import com.pluralsight.data.mysql.MySidesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/sides")
public class SidesController
{

    private MySidesDao sidesDao;

    @Autowired
    public SidesController(MySidesDao sidesDao)
    {
        this.sidesDao = sidesDao;
    }

    @GetMapping
    public List<Sides> getAllSides()
    {
        return sidesDao.getAllSides();
    }

    @GetMapping("/{id}")
    public Sides getSideById(@PathVariable int id)
    {
        return sidesDao.getById(id);
    }
}