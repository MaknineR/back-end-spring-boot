package com.hero.controller;

import com.hero.HeroNotFoundException;
import com.hero.model.Hero;
import com.hero.repository.HeroRepository;
import com.hero.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hero")
@CrossOrigin(origins = "http://localhost:4200")
public class HeroController {

    private final HeroRepository heroRepository;
//------------------------------List Hero----------------------------//
    @Autowired
    public HeroController(HeroRepository heroRepository){
        this.heroRepository=heroRepository;
    }
    @GetMapping("/list")
    public List<Hero> getAllHero(){
        return heroRepository.findAll();
    }
//-----------------------------------------------------------//
//------------------------------Add Hero----------------------------//
    @Autowired
    private HeroService heroService;
    @PostMapping("/ajout")
    public String saveHero(@RequestBody Hero hero) {
        return heroService.save(hero);
    }
//-----------------------------------------------------------------//
    //------------------------ Hero By Id --------------------------//
@GetMapping("/{id}")
public Hero getHeroById(@PathVariable Long id) {
    return heroRepository.findById(id).orElse(null);
}
//------------------------------------------------------------------//
//------------------------ Update Hero By Id --------------------------//
    @PutMapping("/{id}")
    public Hero updateHero(@PathVariable Long id, @RequestBody Hero updatedHero) {
        // Check if entity with given ID exists
        if (heroRepository.existsById(id)) {
            updatedHero.setId(id); // Set ID of updated entity

            return heroRepository.save(updatedHero);
        } else {
            throw new HeroNotFoundException("Could not find hero with ID: " + id);
        }
    }
    //-----------------------------------------------------------------------//
    //----------------------------Delete--------------------------------//
    @DeleteMapping("/{id}")
    public void deleteHero(@PathVariable Long id) {
        // Check if entity with given ID exists
        if (heroRepository.existsById(id)) {
            heroRepository.deleteById(id);
        } else {
            throw new HeroNotFoundException("Could not find hero with ID: " + id);
        }
    }
   //-----------------------------------------------------------------------------//




}
