package br.project.account.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Project Account
 * @author Ronaldo Torre
 */
public class NewDate {

    private Date date;
    private Calendar cal;
    private SimpleDateFormat formatDate;

    public NewDate() {
    }

    public Date GetDate() {
        date = new Date(System.currentTimeMillis());
        return date;
    }

    public String GetDateFormat(String dateformat) {
        date = new Date(System.currentTimeMillis());
        formatDate = new SimpleDateFormat(dateformat);
        return formatDate.format(date);
    }

    public String GetTime() {
        String time[] = new String[3];
        cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));

        time[0] = String.valueOf(cal.get(Calendar.HOUR));
        time[1] = String.valueOf(cal.get(Calendar.MINUTE));
        time[2] = String.valueOf(cal.get(Calendar.SECOND));

        for (int i = 0; i < time.length; i++) {
            if (time[i].length() < 2) {
                time[i] = "0" + time[i];
            }
        }

        return time[0] + ":" + time[1] + ":" + time[2];
    }

    public Date StringToDate(String date, String format) throws ParseException {
        if ((date.isEmpty()) && (format.isEmpty())) {
            throw new IllegalArgumentException("Invalid paramters");
        } else if (date.isEmpty()) {
            throw new IllegalArgumentException("Invalid date");
        } else if (format.isEmpty()) {
            throw new IllegalArgumentException("Invalid format");
        } else {
            this.formatDate = new SimpleDateFormat(format);
            return this.formatDate.parse(date);
        }
    }

    public String DateToString(Date date, String format) {
        if ((date == null) && (format.isEmpty())) {
            throw new IllegalArgumentException("Invalid paramters");
        } else if (format.isEmpty()) {
            throw new IllegalArgumentException("Invalid format");
        } else {
            this.formatDate = new SimpleDateFormat(format);
            return this.formatDate.format(date).toString();
        }
    }
    
}