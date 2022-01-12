package nsi.assd.exam.nsiassdquiz2020.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import nsi.assd.exam.nsiassdquiz2020.Activity.AboutUaeActivity;
import nsi.assd.exam.nsiassdquiz2020.Activity.BookmarkActivity;
import nsi.assd.exam.nsiassdquiz2020.Activity.CategoriesActivity;
import nsi.assd.exam.nsiassdquiz2020.Activity.GuidelineActivity;
import nsi.assd.exam.nsiassdquiz2020.Activity.ImportantTipsActivity;
import nsi.assd.exam.nsiassdquiz2020.Activity.NoteCategoryActivity;
import nsi.assd.exam.nsiassdquiz2020.Activity.NsiBook;
import nsi.assd.exam.nsiassdquiz2020.Activity.UsefulContact;
import nsi.assd.exam.nsiassdquiz2020.Activity.VirtualExam;
import nsi.assd.exam.nsiassdquiz2020.R;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    private Context context;
    private List<String> titles;
    private List<Integer> image;

    public MainAdapter(Context context, List<String> titles, List<Integer> image) {
        this.context = context;
        this.titles = titles;
        this.image = image;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.main_grid_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(titles.get(position));
        holder.imageView.setImageResource(image.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        Intent categoryIntent = new Intent(v.getContext(), CategoriesActivity.class);
                        v.getContext().startActivity(categoryIntent);
                        break;
                    case 1:
                        Intent noteIntent = new Intent(v.getContext(), NoteCategoryActivity.class);
                        v.getContext().startActivity(noteIntent);
                        break;
                    case 2:
                        Intent bookmarksIntent = new Intent(v.getContext(), BookmarkActivity.class);
                        v.getContext().startActivity(bookmarksIntent);
                        break;
                    case 3:
                        Intent tipsIntent = new Intent(v.getContext(), ImportantTipsActivity.class);
                        v.getContext().startActivity(tipsIntent);
                        break;
                    case 4:
                        Intent guidelineIntent = new Intent(v.getContext(), GuidelineActivity.class);
                        v.getContext().startActivity(guidelineIntent);
                        break;
                    case 5:
                        Intent nsiBookIntent = new Intent(v.getContext(), NsiBook.class);
                        v.getContext().startActivity(nsiBookIntent);
                        break;
                    case 6:
                        Intent aboutUaeIntent = new Intent(v.getContext(), AboutUaeActivity.class);
                        v.getContext().startActivity(aboutUaeIntent);
                        break;
                    case 7:
                        Intent contactIntent = new Intent(v.getContext(), UsefulContact.class);
                        v.getContext().startActivity(contactIntent);
                        break;
                    case 8:
                        Intent examIntent = new Intent(v.getContext(), VirtualExam.class);
                        v.getContext().startActivity(examIntent);
                        break;

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.main_image);
            textView = itemView.findViewById(R.id.main_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (getAdapterPosition()) {
                        case 0:
                            Intent categoryIntent = new Intent(v.getContext(), CategoriesActivity.class);
                            v.getContext().startActivity(categoryIntent);
                            break;

                    }
                }
            });

        }
    }
}
