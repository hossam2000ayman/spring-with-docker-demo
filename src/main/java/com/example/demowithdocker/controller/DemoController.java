package com.example.demowithdocker.controller;

import com.example.demowithdocker.entity.Demo;
import com.example.demowithdocker.repository.DemoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/demo")
public class DemoController {
    @Autowired
    DemoRepository demoRepository;

    @GetMapping
    public List<Demo> findAll() {
        return demoRepository.findAll();
    }

    @PostMapping("add")
    public Demo addDemo(@RequestBody Demo demo) {
        return demoRepository.save(demo);
    }

    @PutMapping("update/{id}")
    public Demo updateDemo(@RequestBody Demo newDemo, @PathVariable Long id) {
        Demo existingDemo = getDemo(id);
        if (newDemo.getName() != null) {
            existingDemo.setName(newDemo.getName());
        }
        if (newDemo.getDescription() != null) {
            existingDemo.setDescription(newDemo.getDescription());
        }
        return existingDemo;
    }

    @GetMapping("{id}")
    public Demo getDemo(@PathVariable Long id) {
        return demoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Demo are not found for id = " + id));
    }

    @DeleteMapping("{id}")
    public String deleteDemo(@PathVariable Long id) {
        demoRepository.deleteById(id);
        return "Demo is deleted successfully";
    }
}
