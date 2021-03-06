package es.ucm.fdi.bookssearcher;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class  BooksResultListAdapter extends RecyclerView.Adapter<BooksResultListAdapter.BookViewHolder> {

    private LayoutInflater inflater;
    private List<BookInfo> mBooksData;

    public BooksResultListAdapter(Context context, List<BookInfo> wordList) {
        this.inflater = LayoutInflater.from(context);
        this.mBooksData = wordList;
    }

    /**
     * Un RecyclerView.ViewHolder describe el elemento de una vista y metadata sobre su localización
     * dentro del RecyclerView. Cada ViewHolder mantiene un conjunto de datos. El adapter añade
     * datos a cada ViewHolder para que el LayoutManager lo muestre en la pantalla.
     */
    class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public TextView author;
        public TextView url;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            // Get the layout
            this.title = itemView.findViewById(R.id.titleItem);
            this.author = itemView.findViewById(R.id.authorsItem);
            this.url = itemView.findViewById(R.id.urlItem);

            // Associate with this adapter
            itemView.setOnClickListener(this);
        }

        /**
         * Inicia un nuevo Activity con la intención de mostrar la URL en un browser.
         */
        @Override
        public void onClick(View view) {
            Uri uri = Uri.parse(this.url.getText().toString());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            view.getContext().startActivity(intent);
        }
    }

    /**
     * "Infla" el elemento de una vista y devuelve un nuevo ViewHolder que lo contiene. Este metodo
     * es llamado cuando el RecyclerView necesita un nuevo ViewHolder para representar un item.
     */
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create view from layout
        View itemView = inflater.inflate(R.layout.result_list_item, parent, false);
        return new BookViewHolder(itemView);
    }


    /**
     * Establecer el contenido del item de una vista en una posición dada en el RecyclerView.
     * Esta es llamada por el RecyclerView, por ejemplo, cuando un elemento de la vista se desplaza en
     * la pantalla.
     */
    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        // Retrieve the data for that position
        BookInfo current = mBooksData.get(position);
        // Add the data to the view
        holder.title.setText(current.getTitle());
        holder.author.setText(current.getAuthors());
        holder.url.setText(current.getInfoLink().toString());
    }


    /**
     *  Devuelve el número total de elementos en el conjunto de datos que tiene el Adapter
     */
    @Override
    public int getItemCount() {
        return this.mBooksData.size();
    }



    public void setBooksData(List<BookInfo> data) {
        this.mBooksData = data;
    }
}
