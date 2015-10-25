package com.lymno.myfridge;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * Created by Andre on 25.10.2015.
 */
public class MyDate extends Date {

    private final long MILLIS_IN_DATE = 1000 * 60 * 60 * 24;

    public MyDate(Date l) {
        this(l.getTime());

    }

    public MyDate(long l) {
        super(l);
    }

    public MyDate() {
        super();
    }

    public MyDate stayedTime() {
        return new MyDate(getTime() - new MyDate().getTime());
    }

    public String presentToString(String before) {
        Long time = getTime();

        String s = "";
        if (time < MILLIS_IN_DATE) {
            s = "Продукт испортился";
            return s;

        }
        if (time < 2 * MILLIS_IN_DATE) {
            s = before + time / MILLIS_IN_DATE + " день";
            return s;
        }
        if (time < 5 * MILLIS_IN_DATE) {
            s = before + time / MILLIS_IN_DATE + " дня";
            return s;
        }
        if (time < 14 * MILLIS_IN_DATE) {
            s = before + time / MILLIS_IN_DATE + " дней";
            return s;
        }
        if (time < 31 * MILLIS_IN_DATE) {
            s = before + time / (7L * MILLIS_IN_DATE) + " недели";
            return s;
        }
        if (time < 45 * MILLIS_IN_DATE) {
            s = before + time / (30L * MILLIS_IN_DATE) + " месяц";
            return s;
        }
        if ((time < 135 * MILLIS_IN_DATE) & (time > 45 * MILLIS_IN_DATE)) {
            s = before + time / (30L * MILLIS_IN_DATE) + " месяца";
            return s;
        }
        if (time < 359 * MILLIS_IN_DATE) {
            s = before + time / (30L * MILLIS_IN_DATE) + " месяцев";
            return s;
        }
        if (time < 359*2 * MILLIS_IN_DATE) {
            s = before + time / (356L * MILLIS_IN_DATE) + " год";
            return s;
        }
        if (time < 359*4 * MILLIS_IN_DATE) {
            s = before + time / (356L * MILLIS_IN_DATE) + " года";
            return s;
        }
        s = before + time / (356L * MILLIS_IN_DATE) + " лет";
        return s;
    }

}
