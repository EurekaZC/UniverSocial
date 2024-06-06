package com.example.universocialui.help;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.universocialui.R;
import java.util.List;

public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.FAQViewHolder> {

    private final List<String> questions;
    private final List<String> answers;
    private final boolean[] expanded;

    public FAQAdapter(List<String> questions, List<String> answers) {
        this.questions = questions;
        this.answers = answers;
        this.expanded = new boolean[questions.size()];
    }

    @NonNull
    @Override
    public FAQViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_faq, parent, false);
        return new FAQViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FAQViewHolder holder, int position) {
        holder.questionTextView.setText(questions.get(position));
        holder.answerTextView.setText(answers.get(position));
        holder.answerTextView.setVisibility(expanded[position] ? View.VISIBLE : View.GONE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = holder.getAdapterPosition();
                if (currentPosition != RecyclerView.NO_POSITION) {
                    expanded[currentPosition] = !expanded[currentPosition];
                    notifyItemChanged(currentPosition);
                    if (expanded[currentPosition]) {
                        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
                        animation.setDuration(500);
                        holder.answerTextView.startAnimation(animation);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    static class FAQViewHolder extends RecyclerView.ViewHolder {
        TextView questionTextView;
        TextView answerTextView;

        public FAQViewHolder(@NonNull View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.questionTextView);
            answerTextView = itemView.findViewById(R.id.answerTextView);
        }
    }
}
