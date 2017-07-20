package models;

import java.util.Date;

/**
 * Created by Zeke on 06/07/2017.
 */
public class Stat
{
    private int id;
    private int[] id_listTasks;
    private int sprintDuration;
    private Date beginningDateProject;

    public Stat(int id, int[] id_listTasks, int sprintDuration, Date beginningDateProject)
    {
        this.id = id;
        this.id_listTasks = id_listTasks;
        this.sprintDuration = sprintDuration;
        this.beginningDateProject = beginningDateProject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getId_listTasks() {
        return id_listTasks;
    }

    public void setId_listTasks(int[] id_listTasks) {
        this.id_listTasks = id_listTasks;
    }

    public int getSprintDuration() {
        return sprintDuration;
    }

    public void setSprintDuration(int sprintDuration) {
        this.sprintDuration = sprintDuration;
    }

    public Date getBeginningDateProject() {
        return beginningDateProject;
    }

    public void setBeginningDateProject(Date beginningDateProject) {
        this.beginningDateProject = beginningDateProject;
    }
}
