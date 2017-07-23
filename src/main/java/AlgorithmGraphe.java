import models.Stat;
import models.Task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Zeke on 20/07/2017.
 */
public class AlgorithmGraphe {


    public static String dateToDayName(Date date)
    {
        if (date == null)
            return "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String result = new SimpleDateFormat("EEEE").format(date);
        return result;
    }

    public static Date stringToDate(String strDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date result = format.parse(strDate);
        return result;
    }

    public static double maxTaskHours(List<Task> listTask)
    {
        double result = 0;
        for (Task t : listTask)
        {
            result += t.getDuration();
        }
        return result;
    }



    public static double[] getDatasetTask(ArrayList<Task> listTask, String beginDate, String endDate)
    {
        double[] result = new double[6];
        String[] weekDays = {"lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi"};
        double maxHours = maxTaskHours(listTask);
        int index = 0;

        for (String d : weekDays)
        {
            double dayHours = 0;
            for (Task t : listTask)
            {
                // TO TEST
                String dayTask = t.getTaskDone();
                if (dayTask.equals(d))
                {
                    maxHours -= t.getDuration();
                }
            }
            result[index] = maxHours;
            index++;
        }
        return result;
    }

    public static double[] createOptimalData(double maxTimeTask)
    {
        double[] result;
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

    public static long getDurationSprint(String beginDate, String endDate) throws ParseException {
        Date beg = AlgorithmGraphe.stringToDate(beginDate);
        Date end = AlgorithmGraphe.stringToDate(endDate);

        Calendar calBeg = Calendar.getInstance();
        Calendar calEnd = Calendar.getInstance();
        calBeg.setTime(beg);
        calEnd.setTime(end);

        long res = (calEnd.getTimeInMillis() - calBeg.getTimeInMillis()) / 86400000;

        return res + 1;
    }
}
