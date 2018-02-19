package comq.russia.app_quanlycongviec.model;

import android.icu.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

/**
 * Created by VLADIMIR PUTIN on 2/16/2018.
 */

public class JobInWeek {
    private String title;
    private String description;
    private Date dateFinish;
    private Date hourFinish;

    public JobInWeek() {
    }

    public JobInWeek(String title, String description, Date dateFinish, Date hourFinish) {
        this.title = title;
        this.description = description;
        this.dateFinish = dateFinish;
        this.hourFinish = hourFinish;
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

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    public Date getHourFinish() {
        return hourFinish;
    }

    public void setHourFinish(Date hourFinish) {
        this.hourFinish = hourFinish;
    }


}
