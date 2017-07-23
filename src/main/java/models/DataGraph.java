package models;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by berangerelatouche on 22/07/2017.
 */
public class DataGraph {

    private ArrayList<Task> listTask;
    private String dateBeg;
    private String dateEnd;
    private long sprintDuration;

    public DataGraph(ArrayList<Task> listTask, String dateBeg, String dateEnd, long sprintDuration) {
        this.listTask = listTask;
        this.dateBeg = dateBeg;
        this.dateEnd = dateEnd;
        this.sprintDuration = sprintDuration;
    }

    public ArrayList<Task> getListTask() {
        return listTask;
    }

    public void setListTask(ArrayList<Task> listTask) {
        this.listTask = listTask;
    }

    public String getDateBeg() {
        return dateBeg;
    }

    public void setDateBeg(String dateBeg) {
        this.dateBeg = dateBeg;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public long getSprintDuration() {
        return sprintDuration;
    }

    public void setSprintDuration(long sprintDuration) {
        this.sprintDuration = sprintDuration;
    }
}
