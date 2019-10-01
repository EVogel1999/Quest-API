package com.example.Quest.Demo.Database;

import com.example.Quest.Demo.Database.Schema.Hero;
import com.example.Quest.Demo.Database.Schema.Quest;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private static MongoDatabase database;
    private static MongoCollection heroes;
    private static MongoCollection quests;

    static {
        MongoClientURI uri = new MongoClientURI(System.getenv("MONGODB_CONNECTION"));
        MongoClient client = new MongoClient(uri);
        database = client.getDatabase("test");
        heroes = database.getCollection("heroes");
        quests = database.getCollection("quests");
    }

    public List<Hero> getHeroes() {
        MongoCursor<Document> result = heroes.find().iterator();
        List<Hero> heroes = new ArrayList<>();
        try {
            while (result.hasNext()) {
                heroes.add(Hero.parseDocument(result.next()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            result.close();
        }
        return heroes;
    }

    public List<Quest> getQuests() {
        MongoCursor<Document> result = quests.find().iterator();
        List<Quest> quests = new ArrayList<>();
        try {
            while (result.hasNext()) {
                quests.add(Quest.parseDocument(result.next()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            result.close();
        }
        return quests;
    }

    public Hero getHero(ObjectId id) {
        Bson filter = new Document("_id", id);
        return (Hero) heroes.find(filter).first();
    }

    public Quest getQuest(ObjectId id) {
        Bson filter = new Document("_id", id);
        return (Quest) quests.find(filter).first();
    }

    public void createHero(Hero hero) {
        heroes.insertOne(hero.generateDocument());
    }

    public void createQuest(Quest quest) {
        quests.insertOne(quest.generateDocument());
    }

    public void updateHero(Hero hero) {
        Bson filter = new Document("_id", hero.getId());
        heroes.updateOne(filter, hero.generateDocument());
    }

    public void updateQuest(Quest quest) {
        Bson filter = new Document("_id", quest.getId());
        quests.updateOne(filter, quest.generateDocument());
    }

    public void deleteHero(ObjectId id) {
        Bson filter = new Document("_id", id);
        heroes.deleteOne(filter);
    }

    public void deleteQuest(ObjectId id) {
        Bson filter = new Document("_id", id);
        quests.deleteOne(filter);
    }
}
