package com.ddatabase.show;

import com.ddatabase.show.dom.Author;
import com.ddatabase.show.dom.Book;

import static javax.print.attribute.standard.MediaSizeName.B;

public final class TestDataUtil {

    private TestDataUtil() {

    }

    public static Author getAuthor() {
        return Author.builder()
                .id(22L)
                .name("John")
                .age(56)
                .build();
    }

    public static Author getAuthorB() {
        return Author.builder()
                .id(30)
                .name("John1")
                .age(5)
                .build();
    }

    public static Author getAuthorC() {
        return Author.builder()
                .id(2L)
                .name("pan")
                .age(6)
                .build();
    }

    public static Book getBook() {
        return Book.builder()
                .isbn("dafe")
                .title("rich")
                .author_id(22L)
                .build();
    }
    public static Book getBookB() {
        return Book.builder()
                .isbn("dae")
                .title("1st")
                .author_id(30)
                .build();
    }
    public static Book getBookC() {
        return Book.builder()
                .isbn("de")
                .title("money ")
                .author_id(2L)
                .build();
    }
}
