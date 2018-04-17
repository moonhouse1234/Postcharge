package com.yzcx.postcharge.entity.product;

/**
 * @author liuqingjie
 * @create $date$
 * @description $desc$
 */

public class Content {
    private int id;
    private String name;
    private String max_weight;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMax_weight() {
        return max_weight;
    }

    public void setMax_weight(String max_weight) {
        this.max_weight = max_weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
