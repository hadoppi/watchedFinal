package com.example.watched.adapter;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

import com.example.watched.R;
import com.example.watched.database.entity.ListEntity;
import com.example.watched.database.entity.TvShowEntity;
import com.example.watched.database.entity.ClientEntity;
import com.example.watched.database.entity.EpisodeEntity;
import com.example.watched.util.RecyclerViewItemClickListener;

public class RecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<T> mData;
    private RecyclerViewItemClickListener mListener;


    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView mTextView;

        ViewHolder(TextView textView) {
            super(textView);
            mTextView = textView;
        }
    }

    public RecyclerAdapter(RecyclerViewItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view, parent, false);
        final ViewHolder viewHolder = new ViewHolder(v);
        v.setOnClickListener(view -> mListener.onItemClick(view, viewHolder.getAdapterPosition()));
        v.setOnLongClickListener(view -> {
            mListener.onItemLongClick(view, viewHolder.getAdapterPosition());
            return true;
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        T item = mData.get(position);
        if (item.getClass().equals(TvShowEntity.class))
            holder.mTextView.setText(((TvShowEntity) item).getName());
        if (item.getClass().equals(ClientEntity.class))
            holder.mTextView.setText(((ClientEntity) item).getFirstName() + " " + ((ClientEntity) item).getLastName());
        if (item.getClass().equals(EpisodeEntity.class))
            holder.mTextView.setText(((EpisodeEntity) item).getName());
        if (item.getClass().equals(ListEntity.class))
            holder.mTextView.setText(((ListEntity) item).getName()+" \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t "+((ListEntity) item).getFavoriteShows());

    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        } else {
            return 0;
        }
    }

    public void setData(final List<T> data) {
        if (mData == null) {
            mData = data;
            notifyItemRangeInserted(0, data.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mData.size();
                }

                @Override
                public int getNewListSize() {
                    return data.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    if (mData instanceof TvShowEntity) {
                        return ((TvShowEntity) mData.get(oldItemPosition)).getName().equals(((TvShowEntity) data.get(newItemPosition)).getName());
                    }
                    if (mData instanceof ClientEntity) {
                        return ((ClientEntity) mData.get(oldItemPosition)).getEmail().equals(
                                ((ClientEntity) data.get(newItemPosition)).getEmail());
                    }
                    if (mData instanceof ListEntity) {
                        return ((ListEntity) mData.get(oldItemPosition)).getName().equals(
                                ((ListEntity) data.get(newItemPosition)).getName());
                    }

                    return false;
                }

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    if (mData instanceof TvShowEntity) {
                        TvShowEntity newAccount = (TvShowEntity) data.get(newItemPosition);
                        TvShowEntity oldAccount = (TvShowEntity) mData.get(newItemPosition);
                        return newAccount.getName().equals(oldAccount.getName())
                                && Objects.equals(newAccount.getName(), oldAccount.getName());
                    }
                    if (mData instanceof ClientEntity) {
                        ClientEntity newClient = (ClientEntity) data.get(newItemPosition);
                        ClientEntity oldClient = (ClientEntity) mData.get(newItemPosition);
                        return Objects.equals(newClient.getEmail(), oldClient.getEmail())
                                && Objects.equals(newClient.getFirstName(), oldClient.getFirstName())
                                && Objects.equals(newClient.getLastName(), oldClient.getLastName())
                                && newClient.getPassword().equals(oldClient.getPassword());
                    }
                    if (mData instanceof ListEntity) {
                        ListEntity newList = (ListEntity) data.get(newItemPosition);
                        ListEntity oldList = (ListEntity) mData.get(newItemPosition);
                        return newList.getName().equals(oldList.getName())
                                && Objects.equals(newList.getName(), oldList.getName());

                    }
                    return false;
                }
            });
            mData = data;
            result.dispatchUpdatesTo(this);
        }
    }
}
