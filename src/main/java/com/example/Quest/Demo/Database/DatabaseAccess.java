package com.example.Quest.Demo.Database;

import com.example.Quest.Demo.Database.Schema.Hero;
import com.example.Quest.Demo.Database.Schema.Quest;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
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
        Dotenv dotenv = Dotenv.load();
        MongoClientURI uri = new MongoClientURI(dotenv.get("MONGODB_CONNECTION"));
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

    public Hero getHero(String id) {
        Bson filter = new Document("_id", new ObjectId(id));
        Document doc = (Document) heroes.find(filter).first();
        return Hero.parseDocument(doc);
    }

    public Quest getQuest(String id) {
        Bson filter = new Document("_id", new ObjectId(id));
        Document doc = (Document) quests.find(filter).first();
        return Quest.parseDocument(doc);
    }

    public Hero createHero(Hero hero) {
        Document doc = hero.generateDocument();
        heroes.insertOne(doc);
        return Hero.parseDocument(doc);
    }

    public Quest createQuest(Quest quest) {
        Document doc = quest.generateDocument();
        quests.insertOne(doc);
        return Quest.parseDocument(doc);
    }

    public void updateHero(String id, Hero hero) throws Exception {
        if (id.equals(hero.getId())) {
            Bson filter = new Document("_id", new ObjectId(id));
            heroes.updateOne(filter, new Document("$set", hero.generateDocument()));
        } else {
            throw new Exception("Ids of query and hero to update do not match");
        }
    }

    public void updateQuest(String id, Quest quest) throws Exception{
        if (id.equals(quest.getId())) {
            Bson filter = new Document("_id", new ObjectId(id));
            quests.updateOne(filter, new Document("$set", quest.generateDocument()));
        } else {
            throw new Exception("Ids of query and quest to update do not match");
        }
    }

    public void deleteHero(String id) {
        Bson filter = new Document("_id", new ObjectId(id));
        heroes.deleteOne(filter);
    }

    public void deleteQuest(String id) {
        Bson filter = new Document("_id", new ObjectId(id));
        quests.deleteOne(filter);
    }
}
