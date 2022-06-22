package com.spiderman.blogsweb.serial.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "serial")
public class SerialNumberEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "system_uuid")
    @GenericGenerator(name = "system_uuid",strategy = "uuid")
    @Column(nullable = false,length = 36)
    private String id;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private int num;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
