package nsi.assd.exam.nsiassdquiz2020.Adapter;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import nsi.assd.exam.nsiassdquiz2020.Activity.NotificationsActivity;
import nsi.assd.exam.nsiassdquiz2020.Model.QuestionModel;
import nsi.assd.exam.nsiassdquiz2020.R;

public class QuestionAdapeter extends RecyclerView.Adapter<QuestionAdapeter.viewholder> {
    private List<QuestionModel> list;

    public QuestionAdapeter(List<QuestionModel> list, String category, DeleteListener listener) {
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        String question = list.get(position).getQuestion();
        String answer = list.get(position).getAnswer();
        String optionA = list.get(position).getA();
        String optionB = list.get(position).getB();
        String optionC = list.get(position).getC();
        String optionD = list.get(position).getD();
        holder.setData(question, optionA, optionB, optionC,optionD, answer, position);
    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    class viewholder extends RecyclerView.ViewHolder {

        private TextView question, opt1, opt2, opt3, opt4;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            question = itemView.findViewById(R.id.question);
            opt1 = itemView.findViewById(R.id.opt1);
            opt2 = itemView.findViewById(R.id.opt2);
            opt3 = itemView.findViewById(R.id.opt3);
            opt4 = itemView.findViewById(R.id.opt4);
        }

        private void setData(String question, String optionA, String optionB, String optionC, String optionD, String answer, final int position) {
            this.question.setText(position + 1 + ". " + question);

            this.opt1.setText(String.format("A. %s", optionA));
            this.opt2.setText(String.format("B. %s", optionB));
            this.opt3.setText(String.format("C. %s", optionC));
            this.opt4.setText(String.format("D. %s", optionD));

            if (opt1.getText().toString().isEmpty() || opt1.getText().toString().length() <= 3){
                opt1.setVisibility(View.GONE);
            }else {
                opt1.setVisibility(View.VISIBLE);
            }
            if (opt2.getText().toString().isEmpty()  || opt2.getText().toString().length() <= 3){
                opt2.setVisibility(View.GONE);
            }else {
                opt2.setVisibility(View.VISIBLE);
            }
            if (opt3.getText().toString().isEmpty () || opt3.getText().toString().length() <= 3){
                opt3.setVisibility(View.GONE);
            }else {
                opt3.setVisibility(View.VISIBLE);
            }
            if (opt4.getText().toString().isEmpty() || opt4.getText().toString().length() <= 3){
                opt4.setVisibility(View.GONE);
            }else {
                opt4.setVisibility(View.VISIBLE);
            }
            if (answer.equals(optionA)){
                opt1.setTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")));
            }else {
                opt1.setTextColor(Color.BLACK);
            }
            if (answer.equals(optionB)){
                opt2.setTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")));
            }else {
                opt2.setTextColor(Color.BLACK);
            }
            if (answer.equals(optionC)){
                opt3.setTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")));
            }else {
                opt3.setTextColor(Color.BLACK);
            }
            if (answer.equals(optionD)){
                opt4.setTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")));
            }else {
                opt4.setTextColor(Color.BLACK);
            }

        }
    }

    public interface DeleteListener {
        void onLongClick(int position, String id);
    }
}
