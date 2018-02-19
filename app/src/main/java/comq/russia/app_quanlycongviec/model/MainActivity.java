package comq.russia.app_quanlycongviec.model;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import comq.russia.app_quanlycongviec.R;
import comq.russia.app_quanlycongviec.model.adapter.AdapterJob;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    EditText edtTenCongViec;
    EditText edtNoiDung;
    TextView tvHienNgay;
    Button btnChonNgay;
    TextView tvHienGio;
    Button btnChonGio;
    Button btnThemCongViec;
    ListView lvShowViec;
    Calendar calendar;
    Date dateFinish;
    Date hourFinish;
    ArrayList<JobInWeek> jobInWeekArrayList = new ArrayList<>();
    AdapterJob adapterJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectView();
        loadDefaultInfo();
        checkButtonEvent();
    }

    private void checkButtonEvent() {
        btnChonNgay.setOnClickListener(this);
        btnChonGio.setOnClickListener(this);
        btnThemCongViec.setOnClickListener(this);
        lvShowViec.setOnItemClickListener(this);
        lvShowViec.setOnItemLongClickListener(this);
    }

    private void loadDefaultInfo() {
        /*lay thong tin ve thoi gian hien tai cua he thong*/
        calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String strDate = simpleDateFormat.format(calendar.getTime());
        /*hien thi len man hinh khi khoi chay app*/
        tvHienNgay.setText(strDate);
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        String strHour = simpleDateFormat1.format(calendar.getTime());
        tvHienGio.setText(strHour);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm", Locale.getDefault());
        tvHienGio.setTag(simpleDateFormat2.format(calendar.getTime()));
        dateFinish = calendar.getTime();
        hourFinish = calendar.getTime();
    }

    private void connectView() {
        edtTenCongViec = findViewById(R.id.edt_congViec);
        edtNoiDung = findViewById(R.id.edt_noiDung);
        tvHienNgay = findViewById(R.id.tv_hienNgay);
        btnChonNgay = findViewById(R.id.btn_chonNgay);
        tvHienGio = findViewById(R.id.tv_hienGio);
        btnChonGio = findViewById(R.id.btn_chonGio);
        btnThemCongViec = findViewById(R.id.btn_themCongViec);
        lvShowViec = findViewById(R.id.lv_showViec);
        adapterJob = new AdapterJob(this, R.layout.item_listview, jobInWeekArrayList);
        lvShowViec.setAdapter(adapterJob);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_chonNgay: {
                showDatePickerDialog();
                break;
            }
            case R.id.btn_chonGio: {
                showTimePickerDialog();
                break;
            }
            case R.id.btn_themCongViec: {
                functionAddJob();
                break;
            }
        }
    }

    private void functionAddJob() {
        String titleCongViec = edtTenCongViec.getText() + "";
        String contentCongViec = edtNoiDung.getText() + "";
        JobInWeek jobInWeek = new JobInWeek(titleCongViec, contentCongViec, dateFinish, hourFinish);
        jobInWeekArrayList.add(jobInWeek);
        adapterJob.notifyDataSetChanged();
        edtTenCongViec.setText("");
        edtNoiDung.setText("");
    }

    private void showTimePickerDialog() {
        TimePickerDialog.OnTimeSetListener callback = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                String s = hourOfDay + ":" + minute;
                int hourTemp = hourOfDay;
                if (hourTemp > 12) {
                    hourTemp = hourTemp - 12;
                }
                tvHienGio.setText(hourTemp + ":" + minute + (hourOfDay > 12 ? "PM" : "AM"));
                tvHienGio.setTag(s);
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                hourFinish = calendar.getTime();
            }
        };
        String s = tvHienGio.getTag() + "";
        String strArr[] = s.split(":");
        int hour = Integer.parseInt(strArr[0]);
        int minute = Integer.parseInt(strArr[1]);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, callback, hour, minute, true);
        timePickerDialog.setTitle("Chon gio hoan thanh");
        timePickerDialog.show();
    }

    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                tvHienNgay.setText((dayOfMonth) + "/" + (monthOfYear + 1) + "/" + year);
                calendar.set(year, monthOfYear, dayOfMonth);
                dateFinish = calendar.getTime();
            }
        };
        String s = tvHienNgay.getText() + "";
        String strArr[] = s.split("/");
        int day = Integer.parseInt(strArr[0]);
        int month = Integer.parseInt(strArr[1]) - 1;
        int year = Integer.parseInt(strArr[2]);
        DatePickerDialog pickerDialog = new DatePickerDialog(this, callback, year, month, day);
        pickerDialog.setTitle("Chon ngay hoan thanh");
        pickerDialog.show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this,jobInWeekArrayList.get(i).getDescription(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        jobInWeekArrayList.remove(i);
        adapterJob.notifyDataSetChanged();
        return false;
    }
}
