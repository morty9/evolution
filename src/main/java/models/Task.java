package models;

import java.util.Date;

/**
 * Created by Zeke on 07/07/2017.
 */
public class Task
{
    private int id;
    private String title;
    private String description;
    private int difficulty;
    private int priority;
    private int category;
    private float businessValue;
    private int duration;
    private String status;
    private int id_creator;
    private String taskDone;
    private int[] id_members;

    private String createdAt;
    private String updatedAt;

    public Task() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public float getBusinessValue() {
        return businessValue;
    }

    public void setBusinessValue(float businessValue) {
        this.businessValue = businessValue;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId_creator() {
        return id_creator;
    }

    public void setId_creator(int id_creator) {
        this.id_creator = id_creator;
    }

    public String getTaskDone() {
        return taskDone;
    }

    public void setTaskDone(String taskDone) {
        this.taskDone = taskDone;
    }

    public int[] getId_members() {
        return id_members;
    }

    public void setId_members(int[] id_members) {
        this.id_members = id_members;
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

    /*public Task(Date date)
    {
        this.taskDone = date;
        this.duration = 1;
    }
    public Task()
    {
        this.taskDone = null;
        this.duration = 1;
    }*/
}
