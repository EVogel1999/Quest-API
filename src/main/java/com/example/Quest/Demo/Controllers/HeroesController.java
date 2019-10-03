package com.example.Quest.Demo.Controllers;

import com.example.Quest.Demo.Database.DatabaseAccess;
import com.example.Quest.Demo.Database.Schema.Hero;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/heroes")
public class HeroesController {
    private static DatabaseAccess access = new DatabaseAccess();

    /**
     * Gets all Heroes from the database
     * @return a list of Heroes
     */
    @GetMapping
    public List<Hero> getHeroes() {
        List<Hero> heroes = access.getHeroes();
        return heroes;
    }

    /**
     * Gets a Hero from the database
     * @param id the ID of the hero to get
     * @return the Hero with the specified ID
     */
    @GetMapping("/{id}")
    public Hero getHero(@PathVariable String id) {
        Hero hero = access.getHero(id);
        return hero;
    }

    /**
     * Creates a Hero in the database
     * @param hero the Hero to create
     * @return the newly created Hero with its ID
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Hero createHero(@RequestBody Hero hero) {
        Hero result = access.createHero(hero);
        return result;
    }

    /**
     * Updates a Hero in the database
     * @param id the ID of the Hero to update
     * @param hero the Hero to update with
     * @return the updated Hero
     * @throws Exception if the ID in the route do not match the Hero's ID
     */
    @PatchMapping("/{id}")
    public Hero updateHero(@PathVariable String id, @RequestBody Hero hero) throws Exception {
        access.updateHero(id, hero);
        return hero;
    }

    /**
     * Deletes a Hero in the database
     * @param id the ID of the hero to delete
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHero(@PathVariable String id) {
        access.deleteHero(id);
    }
}
