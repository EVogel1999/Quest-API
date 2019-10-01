package com.example.Quest.Demo.Controllers;

import com.example.Quest.Demo.Database.DatabaseAccess;
import com.example.Quest.Demo.Database.Schema.Hero;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/heroes")
public class HeroesController {
    private static DatabaseAccess access = new DatabaseAccess();

    @GetMapping
    public List<Hero> getHeroes() {
        List<Hero> heroes = access.getHeroes();
        return heroes;
    }

    @GetMapping("/{id}")
    public Hero getHero(@PathVariable String id) {
        Hero hero = access.getHero(id);
        return hero;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Hero createHero(@RequestBody Hero hero) {
        Hero result = access.createHero(hero);
        return result;
    }

    @PatchMapping("/{id}")
    public Hero updateHero(@PathVariable String id, @RequestBody Hero hero) throws Exception {
        access.updateHero(id, hero);
        return hero;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHero(@PathVariable String id) {
        access.deleteHero(id);
    }
}
