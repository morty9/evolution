package models;

/**
 * Created by berangerelatouche on 22/07/2017.
 */
public class Sprint {

    private int id;
    private String title;
    private String beginningDate;
    private String endDate;
    private int id_creator;
    private int[] id_listTasks;
    private String createdAt;
    private String updatedAt;

    public Sprint() {
    }

    public Sprint(int id, String title, String beginningDate, String endDate, int id_creator, int[] id_listTasks, String createdAt, String updatedAt) {
        this.id = id;
        this.title = title;
        this.beginningDate = beginningDate;
        this.endDate = endDate;
        this.id_creator = id_creator;
        this.id_listTasks = id_listTasks;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(String beginningDate) {
        this.beginningDate = beginningDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getId_creator() {
        return id_creator;
    }

    public void setId_creator(int id_creator) {
        this.id_creator = id_creator;
    }

    public int[] getId_listTasks() {
        return id_listTasks;
    }

    public void setId_listTasks(int[] id_listTasks) {
        this.id_listTasks = id_listTasks;
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
