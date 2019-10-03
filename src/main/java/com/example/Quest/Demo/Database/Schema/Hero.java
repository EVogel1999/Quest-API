package com.example.Quest.Demo.Database.Schema;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Hero {
    private String _id;
    private String _name;
    private String _type;
    private int _level;

    public Hero() {
        _id = null;
        _name = "";
        _type = "";
        _level = -1;
    }

    public Hero(String _id, String _name, String _class, int _level) {
        this._id = _id;
        this._name = _name;
        this._type = _class;
        this._level = _level;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public String getType() {
        return _type;
    }

    public void setType(String _type) {
        this._type = _type;
    }

    public int getLevel() {
        return _level;
    }

    public void setLevel(int _level) {
        this._level = _level;
    }

    /**
     * Generates a Document of the Hero to easily save into the database
     * @return Document of the Hero
     */
    public Document generateDocument() {
        Document doc = new Document();

        if (_id == null) {
            doc.append("_id", new ObjectId());
        } else {
            doc.append("_id", new ObjectId(_id));
        }

        doc.append("name", _name);
        doc.append("class", _type);
        doc.append("level", _level);
        return doc;
    }

    /**
     * Parses a Document retrieved from the database
     * @param doc the Document to parse
     * @return a Hero given the parsed Document
     */
    public static Hero parseDocument(Document doc) {
        ObjectId id = doc.getObjectId("_id");
        String _name = doc.getString("name");
        String _type = doc.getString("class");
        int _level = doc.getInteger("level");
        return new Hero(id.toHexString(), _name, _type, _level);
    }
}
