package models;

import java.util.Date;

/**
 * Created by Zeke on 06/07/2017.
 */
public class Stat
{
    private int id;
    private int id_project;
    private String createdAt;
    private String updatedAt;

    public Stat() {
    }

    public Stat(int id, int id_project) {
        this.id = id;
        this.id_project = id_project;
    }

    public Stat(int id, int id_project, String createdAt, String updatedAt) {
        this.id = id;
        this.id_project = id_project;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_project() {
        return id_project;
    }

    public void setId_project(int id_project) {
        this.id_project = id_project;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
