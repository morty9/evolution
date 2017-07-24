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
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
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



    public static double[] getDatasetTask(ArrayList<Task> listTask, String beginDate, String endDate, int duration)
    {
        double[] result = new double[duration];
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

    public static double[] getDatasetBusiness(DataGraph data)
    {
        double[] result = new double[(int)data.getSprintDuration()];

        for (Task t : data.getListTask())
        {
        }

        return null;
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
