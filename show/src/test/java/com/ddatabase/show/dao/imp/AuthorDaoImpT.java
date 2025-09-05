package com.ddatabase.show.dao.imp;

import com.ddatabase.show.TestDataUtil;
import com.ddatabase.show.dom.Author;
import com.ddatabase.show.imp.AuthorDaoImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static javax.swing.text.html.HTML.Tag.P;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImpT {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImp underTest;

    @Test
    void testAuthorById() {
        Author author = TestDataUtil.getAuthor();

        underTest.create(author);

        // Exact match of SQL string and parameters
        verify(jdbcTemplate).update(
                eq("INSERT INTO authors(id, name,age) VALUES (?, ?, ?)"),
                eq(22L),
                eq("John"),
                eq(56)
        );
    }

    @Test
    void readone() {
        underTest.findone(22L);

        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"),
                any(AuthorDaoImp.AuthorMapper.class),
                eq(22L)
        );
    }

    @Test
    void readmany()
    {
        underTest.findmany();
        jdbcTemplate.query(
                eq("SELECT FROM id , name, age FROM authors"),
                any(AuthorDaoImp.AuthorMapper.class)
        );
    }
    @Test
    void update1() {
        Author author = TestDataUtil.getAuthor(); // suppose this returns id=99
        underTest.update1(author.getId(), author);            // old id = 22, new id = 99

        jdbcTemplate.update(
                "UPDATE authors SET id=?,name=?,age=? WHERE id =?",
                22L,
                "John",
                56,
                77
        );
}
@Test
    void delete1() {
        underTest.delete(22L);
        jdbcTemplate.update("DELETE FROM authors WHERE id = ?", 22L);
}

    }
