package com.example.Quest.Demo.Database.Schema;

public class Hero {
    private String _name;
    private String _class;
    private int _level;

    public Hero() {
        _name = "";
        _class = "";
        _level = -1;
    }

    public Hero(String _name, String _class, int _level) {
        this._name = _name;
        this._class = _class;
        this._level = _level;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public int get_level() {
        return _level;
    }

    public void set_level(int _level) {
        this._level = _level;
    }
}
