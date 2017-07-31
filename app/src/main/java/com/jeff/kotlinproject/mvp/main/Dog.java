package com.jeff.kotlinproject.mvp.main;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jeff on 17-7-9.
 */

public class Dog extends RealmObject {
    private String name;
    private int age;


    @PrimaryKey
    private String id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public static synchronized String getText() {
        return 656 + "";
    }
}
