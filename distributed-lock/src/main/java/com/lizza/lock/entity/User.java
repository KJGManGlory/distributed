package com.lizza.lock.entity;

import java.io.Serializable;

/**
 * @Desc:
 * @author: lizza1643@gmail.com
 * @date: 2021-01-05
 */
public class User implements Serializable {

    private Integer id;
    private String name;
    private Double money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
