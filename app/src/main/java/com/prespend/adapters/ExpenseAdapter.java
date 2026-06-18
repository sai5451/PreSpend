package com.prespend.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.prespend.R;
import com.prespend.models.Expense;

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {

    private List<Expense> expenseList;

    public ExpenseAdapter(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvAmount;
        TextView tvMerchant;
        TextView tvCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvMerchant = itemView.findViewById(R.id.tvMerchant);
            tvCategory = itemView.findViewById(R.id.tvCategory);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_expense, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Expense expense = expenseList.get(position);

        holder.tvAmount.setText("₹" + expense.getAmount());
        holder.tvMerchant.setText(expense.getMerchant());
        holder.tvCategory.setText(expense.getCategory());
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }
}