package com.ddatabase.show.dao.imp;

import com.ddatabase.show.TestDataUtil;
import com.ddatabase.show.dom.Author;
import com.ddatabase.show.imp.AuthorDaoImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional // ensures each test runs in a transaction that's rolled back after the test
public class AuthorDaoIntegrationTest {

    @Autowired
    private AuthorDaoImp underTest;

    @Test
    public void testIntegration() {
        Author author = TestDataUtil.getAuthor();
        underTest.create(author);

        Optional<Author> result = underTest.findone(author.getId()); // keep your DAO method name
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testMultipleAuthorsCanBeCreatedAndRead() {
        Author author = TestDataUtil.getAuthor();
        Author author2 = TestDataUtil.getAuthorB();
        Author author3 = TestDataUtil.getAuthorC();

        underTest.create(author);
        underTest.create(author2);
        underTest.create(author3);

        List<Author> result = underTest.findmany(); // keep your DAO method name

        // check size and contents (order-insensitive)
        assertThat(result).hasSize(3)
                .containsExactlyInAnyOrder(author, author2, author3);
    }
    @Test
    void testupdateIntegrtion() {
        Author author = TestDataUtil.getAuthor();
        underTest.create(author);
        author.setName("George");
        underTest.update1(author.getId(),author);
        Optional<Author> result = underTest.findone(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);

    }
    @Test
    void testdeleteIntegrtion() {
        Author author = TestDataUtil.getAuthor();
        underTest.create(author);
        underTest.delete(22L);
        Optional<Author> result = underTest.findone(author.getId());
        assertThat(result).isEmpty();
    }
}
