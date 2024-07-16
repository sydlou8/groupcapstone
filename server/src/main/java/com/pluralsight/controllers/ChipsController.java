package com.pluralsight.controllers;


import com.pluralsight.SandwichModels.Chips;
import com.pluralsight.data.ChipsDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chips")
@CrossOrigin
public class ChipsController
{
    private final ChipsDao chipsDao;

    public ChipsController(ChipsDao chipsDao)
    {
        this.chipsDao = chipsDao;
    }

    @GetMapping
    public ResponseEntity<List<Chips>> getAllChips()
    {
        List<Chips> chips = chipsDao.getAllChips();

        return ResponseEntity.ok(chips);
    }

    @GetMapping("/{chipsId")
    public ResponseEntity<Chips> getChipsById(@PathVariable int chipsId)
    {
        Chips chips = chipsDao.getById(chipsId);

        if (chips != null)
        {
            return ResponseEntity.ok(chips);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Chips> createChips(@RequestBody Chips chips)
    {
        Chips createdChips = chipsDao.create(chips);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdChips);
    }

    @PutMapping("/{chipsId}")
    public ResponseEntity<Void> updateChips(@PathVariable int chipId, @RequestBody Chips chips)
    {
        chipsDao.update(chipId, chips);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{chipsId}")
    public ResponseEntity<Void> deleteChips(@PathVariable int chipsId)
    {
        chipsDao.delete(chipsId);

        return ResponseEntity.noContent().build();
    }
}
