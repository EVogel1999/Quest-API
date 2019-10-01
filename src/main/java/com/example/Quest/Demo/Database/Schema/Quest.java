package com.example.Quest.Demo.Database.Schema;

import java.util.Date;

public class Quest {
    private String _name;
    private String _description;
    private int _reward;
    private boolean _completed;
    private Date _expires;

    public Quest() {
        _name = "";
        _description = "";
        _reward = -1;
        _completed = false;
        _expires = null;
    }

    public Quest(String _name, String _description, int _reward, boolean _completed, Date _expires) {
        this._name = _name;
        this._description = _description;
        this._reward = _reward;
        this._completed = _completed;
        this._expires = _expires;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public int get_reward() {
        return _reward;
    }

    public void set_reward(int _reward) {
        this._reward = _reward;
    }

    public boolean get_completed() {
        return _completed;
    }

    public void set_completed(boolean _completed) {
        this._completed = _completed;
    }

    public Date get_expires() {
        return _expires;
    }

    public void set_expires(Date _expires) {
        this._expires = _expires;
    }
}
