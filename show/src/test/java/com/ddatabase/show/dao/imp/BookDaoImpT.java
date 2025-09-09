package com.ddatabase.show.dao.imp;

import com.ddatabase.show.TestDataUtil;
import com.ddatabase.show.dom.Book;
import com.ddatabase.show.imp.BookDaoImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.postgresql.hostchooser.HostRequirement.any;

@ExtendWith(MockitoExtension.class)
public class BookDaoImpT {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImp updateBookDao;

    @Test
    void updateBook() {
        Book book = TestDataUtil.getBook();

        updateBookDao.create(book);

        verify(jdbcTemplate).update(
                eq("INSERT INTO books(isbn, Title,author_id) VALUES (?, ?, ?)"),
                eq("dafe"),
                eq("money 1st"),
                eq(22L)
        );
    }

    @Test
    void viewbook() {
        updateBookDao.upone("dafe");

        verify(jdbcTemplate).query(
                eq("SELECT isbn, Title, author_id FROM books WHERE Title = ? LIMIT 1"),
                any(BookDaoImp.Bookrowmap.class),
                eq("dafe")
        );
    }
    @Test
    void readmultipleBooks() {
        updateBookDao.readmany();
        verify(jdbcTemplate).query(
                eq("SELECT isbn , title , author_id FROM books"),
                any(BookDaoImp.Bookrowmap.class)
        );
    }
    @Test
    void updatebook(){
        Book book = TestDataUtil.getBook();
        updateBookDao.update(book.getIsbn(),book);
        jdbcTemplate.update(
                "UPDATE books SET isbn = ?,Title = ?,author_id = ? WHERE isbn = ?",
                "dafe","rich",22L,"dafe"
        );
    }
    @Test
    void deletebook()
    {
        updateBookDao.delete("dae");
        verify(jdbcTemplate).update(
                "DELETE FROM books WHERE isbn = ?",
                "dae"
        );

    }
}
