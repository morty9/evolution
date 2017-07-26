import models.DataGraph;
import models.Stat;
import models.Task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Zeke on 20/07/2017.
 */
public class AlgorithmGraph {


    public static String dateToDayName(Date date) {
        if (date == null)
            return "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String result = new SimpleDateFormat("EEEE").format(date);
        return result;
    }

    public static Date stringToDate(String strDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date result = format.parse(strDate);
        return result;
    }

    public static double maxTaskHours(ArrayList<Task> listTask) {
        double result = 0;
        for (Task t : listTask)
        {
            result += t.getDuration();
        }
        return result;
    }

    /**
     * \fn double[] getDatasetTask(DataGraph data)
     * \brief Set a list of data to build the graph
     * \details Build a double[] from the datagraph by checking the date and tasks
     *
     * \param data the datagraph object
     * \return the double[] which contain the data for graph
     */
    public static double[] getDatasetTask(DataGraph data) {
        double[] result = new double[(int)data.getSprintDuration()];
        String[] weekDays = data.getListDateName();
        double maxHours = maxTaskHours(data.getListTask());
        int index = 0;

        for (String d : weekDays)
        {
            for (Task t : data.getListTask())
            {
                String daysTask = t.getTaskDone();
                if (daysTask != null)
                    if (daysTask.equals(d))
                        maxHours -= t.getDuration();
            }
            result[index] = maxHours;
            index++;
        }
        return result;
    }

    /**
     * \fn double[] getDatasetBusiness(DataGraph data)
     * \brief Set a list of data to build the graph
     * \details Build a double[] from the datagraph by checking the date and tasks
     *
     * \param data the datagraph object
     * \return the double[] which contain the data for graph
     */
    public static double[] getDatasetBusiness(DataGraph data) {
        double[] result = new double[(int)data.getSprintDuration()];
        String[] weekDays = data.getListDateName();
        double businessSpend = 0;
        int index = 0;

        for (String d : weekDays)
        {
            for (Task t : data.getListTask())
            {
                String dayTask = t.getTaskDone();
                if (dayTask != null)
                    if (dayTask.equals(d))
                        businessSpend += t.getBusinessValue();
            }
            result[index] = businessSpend;
            index++;
        }
        return result;
    }

    /**
     * \fn double[] createOptimalData(double maxTimeTask)
     * \brief Set a list of data to build the optimal graph
     * \details Build a double[] from the datagraph by checking the maximum hours of tasks, to draw the optimal graph
     *
     * \param maxTimeTask the int of the maximum hours of all tasks
     * \return the double[] which contain the data for graph
     */
    public static double[] createOptimalData(double maxTimeTask) {
        double inc = maxTimeTask / 5;
        double value1 = maxTimeTask;
        double value2 = value1 - inc;
        double value3 = value2 - inc;
        double value4 = value3 - inc;
        double value5 = value4 - inc;
        double value6 = 0;

        return new double[] {
                value1, value2, value3, value4, value5, value6
        };
    }

    /**
     * \fn long getDurationSprint(String beginDate, String endDate) throws ParseException
     * \brief Get the duration between 2 date
     * \details Calcul the duration between 2 date
     *
     * \param beginDate the begin date in string
     * \param endDate the end date in string
     * \return the long which contain the number of days between 2 date
     */
    public static long getDurationSprint(String beginDate, String endDate) throws ParseException {
        int dayDuration = 3600 * 24;

        String formatBeginDate = beginDate.substring(0, 10);
        String formatEndDate = endDate.substring(0, 10);

        formatBeginDate = formatBeginDate.replaceAll("-", "/");
        formatEndDate = formatEndDate.replaceAll("-", "/");

        Date dateBeg = stringToDate(formatBeginDate);
        Date dateEnd = stringToDate(formatEndDate);

        long result = Math.abs(dateEnd.getTime() - dateBeg.getTime()) / (dayDuration * 1000);
        return result + 1;
    }

    /**
     * \fn ArrayList<Date> getListDate(String strDateBeg, String strDateEnd) throws ParseException
     * \brief Get the list of date between 2 dates
     * \details get the list of date between 2 dates
     *
     * \param strDateBeg the begin date in string
     * \param strDateEnd the end date in string
     * \return the list of date
     */
    public static ArrayList<Date> getListDate(String strDateBeg, String strDateEnd) throws ParseException {
        Date dateBeg = stringToDate(strDateBeg);
        Date dateEnd = stringToDate(strDateEnd);
        ArrayList<Date> listDate = new ArrayList();

        Calendar cal = Calendar.getInstance();
        cal.setTime(dateBeg);
        while (cal.getTime().before(dateEnd)) {
            listDate.add(cal.getTime());
            cal.add(Calendar.DATE, 1);
        }
        listDate.add(cal.getTime());
        return listDate;
    }
}
