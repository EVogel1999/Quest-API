package com.example.Quest.Demo.Database.Schema;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Date;

public class Quest {
    private String _id;
    private String _name;
    private String _description;
    private int _reward;
    private boolean _completed;
    private Date _expires;

    public Quest() {
        _id = null;
        _name = "";
        _description = "";
        _reward = -1;
        _completed = false;
        _expires = null;
    }

    public Quest(String _id, String _name, String _description, int _reward, boolean _completed, Date _expires) {
        this._id = _id;
        this._name = _name;
        this._description = _description;
        this._reward = _reward;
        this._completed = _completed;
        this._expires = _expires;
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

    public String getDescription() {
        return _description;
    }

    public void setDescription(String _description) {
        this._description = _description;
    }

    public int getReward() {
        return _reward;
    }

    public void setReward(int _reward) {
        this._reward = _reward;
    }

    public boolean getCompleted() {
        return _completed;
    }

    public void setCompleted(boolean _completed) {
        this._completed = _completed;
    }

    public Date getExpires() {
        return _expires;
    }

    public void setExpires(Date _expires) {
        this._expires = _expires;
    }

    /**
     * Generates a Document of the Quest to easily save into the database
     * @return Document of the Quest
     */
    public Document generateDocument() {
        Document doc = new Document();

        if (_id == null) {
            doc.append("_id", new ObjectId());
        } else {
            doc.append("_id", new ObjectId(_id));
        }

        doc.append("name", _name);
        doc.append("description", _description);
        doc.append("reward", _reward);
        doc.append("completed", _completed);
        doc.append("expires", _expires);
        return doc;
    }

    /**
     * Parses a Document retrieved from the database
     * @param doc the Document to parse
     * @return a Quest given the parsed Document
     */
    public static Quest parseDocument(Document doc) {
        ObjectId id = doc.getObjectId("_id");
        String name = doc.getString("name");
        String description = doc.getString("description");
        int reward = doc.getInteger("reward");
        boolean completed = doc.getBoolean("completed");
        Date expires = doc.getDate("expires");
        return new Quest(id.toHexString(), name, description, reward, completed, expires);
    }
}
