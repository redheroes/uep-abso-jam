package pl.redexperts.utils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public final static String SQLITE_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    public final static String SUFFIX_SECONDS_ZONE = ":ssZ";
    private final static String DATE_FORMAT_UI = "dd.MM.yyyy";
    private final static String T_SEPARATOR = "'T'";
    private final static String TAG = DateUtils.class.getSimpleName();
    private final static String TIME_FORMAT_UI = "HH:mm";
    public final static String API_DATETIME_FORMAT = DATE_FORMAT_UI + T_SEPARATOR + TIME_FORMAT_UI + SUFFIX_SECONDS_ZONE;

    public static String formatAsDate(Date datetime) {
        return formatUsingPattern(datetime, DATE_FORMAT_UI);
    }

    public static String formatAsTime(Date datetime) {
        return formatUsingPattern(datetime, TIME_FORMAT_UI);
    }

    public static String formatToUserFormat(int day, int month, int year) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        return formatToUserFormat(calendar.getTime());
    }

    public static String formatToUserFormat(Date date) {
        return (null == date) ? "" : formatUsingPattern(date, DATE_FORMAT_UI);
    }

    public static String formatTimeToUserFormat(Date date) {
        return (null == date) ? "" : formatUsingPattern(date, TIME_FORMAT_UI);
    }

    public static String formatToUserFormat(int hourOfDay, int minute) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);

        return formatUsingPattern(calendar.getTime(), TIME_FORMAT_UI);
    }

    private static String formatUsingPattern(Date date, String pattern) {

        return new SimpleDateFormat(pattern, Locale.getDefault()).format(date);
    }

    public static DatePickerDialog getPickerDate(Context context,
                                                 DatePickerDialog.OnDateSetListener listener,
                                                 String date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        if (!TextUtils.isEmpty(date)) {
            calendar.setTime(new SimpleDateFormat(DATE_FORMAT_UI, Locale.US).parse(date));
        }
        return new DatePickerDialog(context, listener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
    }

    public static TimePickerDialog getPickerTime(Context context,
                                                 TimePickerDialog.OnTimeSetListener listener,
                                                 String date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        if (!TextUtils.isEmpty(date)) {
            calendar.setTime(new SimpleDateFormat(TIME_FORMAT_UI, Locale.US).parse(date));
        }
        return new TimePickerDialog(context, listener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
    }

    public static boolean isTomorrow(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);

        return date.after(calendar.getTime());
    }

    public static Date parseToApiFormat(String date) {
        return parseUsingPattern(date, DATE_FORMAT_UI);
    }

    public static Date parseToApiFormat(String date, String time) {
        SimpleDateFormat sdf = new SimpleDateFormat(SUFFIX_SECONDS_ZONE);

        return parseUsingPattern(date + "T" + time + sdf.format(new Date()), API_DATETIME_FORMAT);
    }

    private static Date parseUsingPattern(String date, String pattern) {
        try {
            return new SimpleDateFormat(pattern, Locale.US).parse(date);
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
            return null;
        }
    }
}