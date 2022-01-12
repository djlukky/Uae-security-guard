package nsi.assd.exam.nsiassdquiz2020.Adapter;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;
import nsi.assd.exam.nsiassdquiz2020.Activity.NoteSetsActivity;
import nsi.assd.exam.nsiassdquiz2020.Activity.SetsActivity;
import nsi.assd.exam.nsiassdquiz2020.Model.CategoryModel;
import nsi.assd.exam.nsiassdquiz2020.Model.NoteCategoryModel;
import nsi.assd.exam.nsiassdquiz2020.R;

public class NoteCategoryAdapter extends RecyclerView.Adapter<NoteCategoryAdapter.viewholder> {
    private List<NoteCategoryModel> noteCategoryModelList;

    public NoteCategoryAdapter(List<NoteCategoryModel> noteCategoryModelList) {

        this.noteCategoryModelList = noteCategoryModelList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.setData(noteCategoryModelList.get(position).getUrl(), noteCategoryModelList.get(position).getName(), position);

    }

    @Override
    public int getItemCount() {
        return noteCategoryModelList.size();
    }

    class viewholder extends RecyclerView.ViewHolder {
        private CircleImageView imageview;
        private TextView title;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            imageview = itemView.findViewById(R.id.image_view);
            title = itemView.findViewById(R.id.title);
        }

        private void setData(String url, final String title, final int position) {
            Glide.with(itemView.getContext()).load(url).into(imageview);
            this.title.setText(title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent setIntent = new Intent(itemView.getContext(), NoteSetsActivity.class);
                    setIntent.putExtra("title", title);
                    setIntent.putExtra("position", position);
                    itemView.getContext().startActivity(setIntent);
                }
            });

        }
    }
}

