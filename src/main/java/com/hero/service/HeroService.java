package com.hero.service;

import com.hero.model.Hero;
import com.hero.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class HeroService {

    @Autowired
    private HeroRepository repo;
    public List<Hero> listAll(){
        return repo.findAll();
    }
    public String save(Hero hero){
        repo.save(hero);
        return null;
    }
    public Hero get(Long id){
        return  repo.findById(id).get();
    }
    public void delete(Long id){
        repo.deleteById(id);
    }



}
