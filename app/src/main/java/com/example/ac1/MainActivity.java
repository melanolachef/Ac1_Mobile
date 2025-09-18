package com.example.ac1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Book> bookList = new ArrayList<>();

    private EditText editTextBookTitle;
    private EditText editTextBookAuthor;
    private CheckBox checkBoxIsRead;
    private Button buttonAddBook;
    private LinearLayout bookListContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextBookTitle = findViewById(R.id.editTextBookTitle);
        editTextBookAuthor = findViewById(R.id.editTextBookAuthor);
        checkBoxIsRead = findViewById(R.id.checkBoxIsRead);
        buttonAddBook = findViewById(R.id.buttonAddBook);
        bookListContainer = findViewById(R.id.bookListContainer);

        buttonAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBookToList();
            }
        });
    }

    private void addBookToList() {
        String title = editTextBookTitle.getText().toString().trim();
        String author = editTextBookAuthor.getText().toString().trim();
        boolean isRead = checkBoxIsRead.isChecked();


        if (title.isEmpty() || author.isEmpty()) {
            Toast.makeText(this, "Preencha o título e o autor.", Toast.LENGTH_SHORT).show();
            return;
        }

        Book newBook = new Book(title, author, isRead);
        bookList.add(newBook);

        updateBookListView();

        editTextBookTitle.setText("");
        editTextBookAuthor.setText("");
        checkBoxIsRead.setChecked(false);

        Toast.makeText(this, "Livro adicionado!", Toast.LENGTH_SHORT).show();
    }

    private void updateBookListView() {
        bookListContainer.removeAllViews();

        for (Book book : bookList) {

            TextView bookTextView = new TextView(this);

            String statusText = book.isRead() ? " (Lido)" : " (Não lido)";
            String displayText = book.getTitle() + " - " + book.getAuthor() + statusText;

            bookTextView.setText(displayText);
            bookTextView.setTextSize(18f);
            bookTextView.setPadding(0, 8, 0, 8);

            if (book.isRead()) {
                bookTextView.setTextColor(Color.parseColor("#008000"));
            } else {
                bookTextView.setTextColor(Color.parseColor("#FF0000"));
            }


            bookListContainer.addView(bookTextView);
        }
    }
}