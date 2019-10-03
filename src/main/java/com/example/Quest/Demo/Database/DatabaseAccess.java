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

    /**
     * Instantiates the MongoDB Atlas connection and gets the corresponding Databases and Collections
     */
    static {
        Dotenv dotenv = Dotenv.load();
        MongoClientURI uri = new MongoClientURI(dotenv.get("MONGODB_CONNECTION"));  // Get hidden MongoDB connection string in .env file
        MongoClient client = new MongoClient(uri);
        database = client.getDatabase("test");
        heroes = database.getCollection("heroes");
        quests = database.getCollection("quests");
    }

    /**
     * Gets all the Heroes from the database
     * @return a list of Heroes
     */
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

    /**
     * Gets all the Quests from the database
     * @return a list of Quests
     */
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

    /**
     * Get a Hero from the database by ID
     * @param id the ID of the Hero to get
     * @return the Hero with the specified ID
     */
    public Hero getHero(String id) {
        Bson filter = new Document("_id", new ObjectId(id));
        Document doc = (Document) heroes.find(filter).first();
        return Hero.parseDocument(doc);
    }

    /**
     * Get a Quest from the database by ID
     * @param id the ID of the Quest to Get
     * @return the Quest with the specified ID
     */
    public Quest getQuest(String id) {
        Bson filter = new Document("_id", new ObjectId(id));
        Document doc = (Document) quests.find(filter).first();
        return Quest.parseDocument(doc);
    }

    /**
     * Creates a Hero in the database
     * @param hero the Hero to create
     * @return the created Hero
     */
    public Hero createHero(Hero hero) {
        Document doc = hero.generateDocument();
        heroes.insertOne(doc);
        return Hero.parseDocument(doc);
    }

    /**
     * Creates a Quest in the database
     * @param quest the Quest to create
     * @return the created Quest
     */
    public Quest createQuest(Quest quest) {
        Document doc = quest.generateDocument();
        quests.insertOne(doc);
        return Quest.parseDocument(doc);
    }

    /**
     * Updates a Hero in the database
     * @param id the ID of the Hero to update
     * @param hero the Hero to update with
     * @throws Exception if the ID does not match the Hero's ID
     */
    public void updateHero(String id, Hero hero) throws Exception {
        if (id.equals(hero.getId())) {
            Bson filter = new Document("_id", new ObjectId(id));
            heroes.updateOne(filter, new Document("$set", hero.generateDocument()));
        } else {
            throw new Exception("Ids of query and hero to update do not match");
        }
    }

    /**
     * Updates a Quest in the database
     * @param id the ID of the Quest to update
     * @param quest the Quest to update with
     * @throws Exception if the ID does not match the Quest's ID
     */
    public void updateQuest(String id, Quest quest) throws Exception{
        if (id.equals(quest.getId())) {
            Bson filter = new Document("_id", new ObjectId(id));
            quests.updateOne(filter, new Document("$set", quest.generateDocument()));
        } else {
            throw new Exception("Ids of query and quest to update do not match");
        }
    }

    /**
     * Deletes a Hero from the database
     * @param id the ID of the Hero to delete
     */
    public void deleteHero(String id) {
        Bson filter = new Document("_id", new ObjectId(id));
        heroes.deleteOne(filter);
    }

    /**
     * Deletes a Quest from the database
     * @param id the ID of the Quest to Delete
     */
    public void deleteQuest(String id) {
        Bson filter = new Document("_id", new ObjectId(id));
        quests.deleteOne(filter);
    }
}
