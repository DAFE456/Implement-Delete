package com.ddatabase.show.imp;

import com.ddatabase.show.dom.Book;

import java.util.Optional;
import java.util.List;

public interface BookDao {
    void create(Book book);

    Optional<Book> upone(String title);

    List<Book>  readmany();

    void update(String isbn, Book book);
}
