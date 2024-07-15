package com.pluralsight.controllers;


import com.pluralsight.models.toppings.Side;
import com.pluralsight.data.mysql.MySidesDao;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sides")
public class SidesController
{

    private MySidesDao sidesDao;

    public SidesController(MySidesDao sidesDao)
    {
        this.sidesDao = sidesDao;
    }

    @GetMapping
    public List<Side> getAllSides()
    {
        return sidesDao.getAllSides();
    }

    @GetMapping("/{id}")
    public Side getSideById(@PathVariable int id)
    {
        return sidesDao.getById(id);
    }
}