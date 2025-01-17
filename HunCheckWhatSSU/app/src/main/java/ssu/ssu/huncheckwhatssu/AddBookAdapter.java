package ssu.ssu.huncheckwhatssu;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import ssu.ssu.huncheckwhatssu.utilClass.Book;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AddBookAdapter extends RecyclerView.Adapter<AddBookAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Book> bookList;

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener listener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public AddBookAdapter(ArrayList<Book> bookList, Context context) {
        this.context = context;
        this.bookList = bookList;
    }

    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView isbn10;
        TextView author;
        TextView publisher;
        TextView pubdate;
        TextView price;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.add_book_item_title);
            this.isbn10 = itemView.findViewById(R.id.add_book_item_ISBN_text);
            this.author = itemView.findViewById(R.id.add_book_item_author_text);
            this.publisher = itemView.findViewById(R.id.add_book_item_publisher_text);
            this.pubdate = itemView.findViewById(R.id.add_book_item_pubdate_text);
            this.price = itemView.findViewById(R.id.add_book_item_price_text);
            this.image = itemView.findViewById(R.id.add_book_item_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        if (listener!= null) {
                            listener.onItemClick(v, position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.add_book_recyclerview_item, parent, false);
        MyViewHolder viewholder = new MyViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.title.setText(book.getTitle());
        holder.price.setText(String.valueOf(book.getOriginal_Price()));
        holder.pubdate.setText(book.getPubDate());
        holder.publisher.setText(book.getPublisher());
        holder.isbn10.setText(book.getIsbn10());
        holder.author.setText(book.getAuthor());
        Glide.with(this.context).load(book.getImage()).into(holder.image);
    }



    @Override
    public int getItemCount() {
        return this.bookList.size();
    }
}
