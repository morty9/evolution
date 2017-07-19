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
    private int id_category;
    private String color;
    private float businessValue;

    private int duration;
    private String status;
    private int id_creator;
    private int[] id_Listmember;


    public Task(int id, String title, String description, int difficulty, int priority, int id_category, String color, float businessValue, int duration, String status, int id_creator, int[] id_Listmember) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.priority = priority;
        this.id_category = id_category;
        this.color = color;
        this.businessValue = businessValue;
        this.duration = duration;
        this.status = status;
        this.id_creator = id_creator;
        this.id_Listmember = id_Listmember;
    }

    public Task()
    { }

    public Task(int id)
    {
        this.id = id;
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

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public int[] getId_Listmember() {
        return id_Listmember;
    }

    public void setId_Listmember(int[] id_Listmember) {
        this.id_Listmember = id_Listmember;
    }
}
