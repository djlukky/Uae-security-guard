package nsi.assd.exam.nsiassdquiz2020.Adapter;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;
import nsi.assd.exam.nsiassdquiz2020.Model.NotificationModel;
import nsi.assd.exam.nsiassdquiz2020.R;


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.Viewholder> {

    private List<NotificationModel> notificationModelList;

    public NotificationAdapter(List<NotificationModel> notificationModelList) {
        this.notificationModelList = notificationModelList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.setData(notificationModelList.get(position).getUrl(), notificationModelList.get(position).getSubject()
                , notificationModelList.get(position).getMessage(), notificationModelList.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return notificationModelList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {
        private CircleImageView imageView;
        private TextView subject;
        private TextView message;
        private TextView date;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.notification_image);
            subject = itemView.findViewById(R.id.notification_t1);
            message = itemView.findViewById(R.id.notification_t2);
            date = itemView.findViewById(R.id.notification_date);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        private void setData(final String url, final String subject, final String message, String date) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
            String currentDate = simpleDateFormat.format(calendar.getTime());
            String currentTime = timeFormat.format(calendar.getTime());

            Glide.with(itemView.getContext()).load(url).into(imageView);
            this.date.setText(currentDate + ", " + currentTime);
            this.subject.setText(subject);
            this.message.setText(message);
        }
    }
}