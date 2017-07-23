package models;

/**
 * Created by berangerelatouche on 22/07/2017.
 */
public class Project {

    private int id;
    private String title;
    private boolean status;
    private int id_creator;
    private int[] id_members;
    private int[] id_sprint;
    private String createdAt;
    private String updatedAt;

    public Project() {
    }

    public Project(int id, String title, boolean status, int id_creator, int[] id_members, int[] id_sprint, String createdAt, String updatedAt) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.id_creator = id_creator;
        this.id_members = id_members;
        this.id_sprint = id_sprint;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId_creator() {
        return id_creator;
    }

    public void setId_creator(int id_creator) {
        this.id_creator = id_creator;
    }

    public int[] getId_members() {
        return id_members;
    }

    public void setId_members(int[] id_members) {
        this.id_members = id_members;
    }

    public int[] getId_sprint() {
        return id_sprint;
    }

    public void setId_sprint(int[] id_sprint) {
        this.id_sprint = id_sprint;
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
