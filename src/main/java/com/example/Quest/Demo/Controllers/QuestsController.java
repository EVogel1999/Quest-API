package com.example.Quest.Demo.Controllers;

import com.example.Quest.Demo.Database.DatabaseAccess;
import com.example.Quest.Demo.Database.Schema.Quest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quests")
public class QuestsController {
    DatabaseAccess access = new DatabaseAccess();

    /**
     * Gets all the Quests from the database
     * @return a Quest list
     */
    @GetMapping
    public List<Quest> getQuests() {
        return access.getQuests();
    }

    /**
     * Gets a Quest from the database by ID
     * @param id the ID of the Quest
     * @return the quest with a specified ID
     */
    @GetMapping("/{id}")
    public Quest getQuest(@PathVariable String id) {
        return access.getQuest(id);
    }

    /**
     * Creates a Quest in the database
     * @param quest the Quest to create
     * @return the newly created Quest with its ID
     */
    @PostMapping
    public Quest createQuest(@RequestBody Quest quest) {
        return access.createQuest(quest);
    }

    /**
     * Updates a Quest in the database
     * @param id the id of the Quest to update
     * @param quest the Quest to update with
     * @return the updated Quest
     * @throws Exception if the ID of the route do not match the Quest's ID
     */
    @PatchMapping("/{id}")
    public Quest updateQuest(@PathVariable String id, @RequestBody Quest quest) throws Exception {
        access.updateQuest(id, quest);
        return quest;
    }

    /**
     * Deletes a Quest in the database
     * @param id the id of the Quest to delete
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuest(@PathVariable String id) {
        access.deleteQuest(id);
    }
}
