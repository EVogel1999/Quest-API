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

    @GetMapping
    public List<Quest> getQuests() {
        return access.getQuests();
    }

    @GetMapping("/{id}")
    public Quest getQuest(@PathVariable String id) {
        return access.getQuest(id);
    }

    @PostMapping
    public Quest createQuest(@RequestBody Quest quest) {
        return access.createQuest(quest);
    }

    @PatchMapping("/{id}")
    public Quest updateQuest(@PathVariable String id, @RequestBody Quest quest) throws Exception {
        access.updateQuest(id, quest);
        return quest;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuest(@PathVariable String id) {
        access.deleteQuest(id);
    }
}
