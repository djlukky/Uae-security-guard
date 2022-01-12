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
import nsi.assd.exam.nsiassdquiz2020.Model.ContactModel;
import nsi.assd.exam.nsiassdquiz2020.Model.NotificationModel;
import nsi.assd.exam.nsiassdquiz2020.R;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.Viewholder> {
    private List<ContactModel> contactModelList;

    public ContactAdapter(List<ContactModel> contactModelList) {
        this.contactModelList = contactModelList;
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.setData(contactModelList.get(position).getUrl(), contactModelList.get(position).getDepartment()
                , contactModelList.get(position).getContact());
    }

    @Override
    public int getItemCount() {
        return contactModelList.size();
    }
    class Viewholder extends RecyclerView.ViewHolder {
        private CircleImageView imageView;
        private TextView department;
        private TextView contactNumber;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.contact_image);
            department = itemView.findViewById(R.id.contact_t1);
            contactNumber = itemView.findViewById(R.id.contact_t2);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        private void setData(final String url, final String department, final String contactNumber) {

            Glide.with(itemView.getContext()).load(url).into(imageView);
            this.department.setText(department);
            this.contactNumber.setText(contactNumber);
        }
    }
}
