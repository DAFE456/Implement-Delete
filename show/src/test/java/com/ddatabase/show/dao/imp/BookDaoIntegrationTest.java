package com.ddatabase.show.dao.imp;

import com.ddatabase.show.TestDataUtil;
import com.ddatabase.show.dom.Author;
import com.ddatabase.show.dom.Book;
import com.ddatabase.show.imp.AuthorDaoImp;
import com.ddatabase.show.imp.BookDaoImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookDaoIntegrationTest {

    private final BookDaoImp bookDao;
    private final AuthorDaoImp authorDao;

    @Autowired
    public BookDaoIntegrationTest(BookDaoImp bookDao, AuthorDaoImp authorDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }

    @Test
    void testBookIntegration() {
        // Create and insert an author
        Author author = TestDataUtil.getAuthor();
        authorDao.create(author);

        // Create a book and assign the author's ID
        Book book = TestDataUtil.getBook();
        bookDao.create(book);

        // Retrieve the book by title
        Optional<Book> result = bookDao.upone(book.getTitle());
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(book.getTitle(), result.get().getTitle());
    }

    @Test
    void testReadMultipleBooks() {
        // Create and insert an author
        Author author = TestDataUtil.getAuthor();
        authorDao.create(author);

        // Create multiple books
        Book book1 = TestDataUtil.getBook();
        book1.setAuthor_id(author.getId());
        Book book2 = TestDataUtil.getBookB();
        book2.setAuthor_id(author.getId());
        Book book3 = TestDataUtil.getBookC();
        book3.setAuthor_id(author.getId());

        bookDao.create(book1);
        bookDao.create(book2);
        bookDao.create(book3);

        // Retrieve all books
        List<Book> result = bookDao.readmany();

        // Assertions
        assertThat(result).hasSize(3);
        assertThat(result).containsExactlyInAnyOrder(book1, book2, book3);
    }
    @Test
    void testupdatebookintegration()
    {
        Author author = TestDataUtil.getAuthor();
        authorDao.create(author);
        Book book = TestDataUtil.getBook();
        book.setAuthor_id(author.getId());
        bookDao.create(book);
        book.setTitle("WWE CHAMPIONS");
        bookDao.update(book.getIsbn(),book);
        Optional<Book> result = bookDao.upone(book.getTitle());
        assertThat(result.isPresent());
        assertThat(result.get()).isEqualTo(book);

    }
    @Test
    void testdeletebookintegration()
    {
        Author author = TestDataUtil.getAuthor();
        authorDao.create(author);
        Book book = TestDataUtil.getBook();
        book.setAuthor_id(author.getId());
        bookDao.delete(book.getIsbn());
        Optional<Book> result = bookDao.upone(book.getIsbn());
        assertThat(result.isEmpty());

    }
}
