package comq.russia.app_quanlycongviec.model.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import comq.russia.app_quanlycongviec.R;
import comq.russia.app_quanlycongviec.model.JobInWeek;

/**
 * Created by VLADIMIR PUTIN on 2/16/2018.
 */

public class AdapterJob extends ArrayAdapter<JobInWeek> {
    Context context;
    int resource;
    ArrayList<JobInWeek> jobInWeekArrayList;

    public AdapterJob(@NonNull Context context, int resource, ArrayList objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.jobInWeekArrayList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview, parent, false);
            viewHolder.tvTenKQ = convertView.findViewById(R.id.tv_tenKetQua);
            viewHolder.tvMoTaKQ = convertView.findViewById(R.id.tv_moTaKetQua);
            viewHolder.tvNgayKQ = convertView.findViewById(R.id.tv_listNgay);
            viewHolder.tvGioKQ = convertView.findViewById(R.id.tv_listGio);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        JobInWeek job = jobInWeekArrayList.get(position);
        viewHolder.tvTenKQ.setText(job.getTitle());
        viewHolder.tvMoTaKQ.setText(job.getDescription());
        viewHolder.tvNgayKQ.setText(job.getDateFinish() + "");
        viewHolder.tvGioKQ.setText(job.getHourFinish() + "");
        return convertView;
    }

    public class ViewHolder {
        TextView tvTenKQ;
        TextView tvMoTaKQ;
        TextView tvNgayKQ;
        TextView tvGioKQ;
    }
}
