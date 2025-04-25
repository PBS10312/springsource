package com.example.book.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.stream.IntStream;

import com.example.book.entity.Book;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testInsert() {
        // 20권
        IntStream.rangeClosed(1, 20).forEach(i -> {
            Book book = Book.builder()
                    .title("book title" + i)
                    .author("" + i)
                    .price(10000 + i)
                    .build();
            bookRepository.save(book);
        });
    }

    @Test
    public void testList() {
        bookRepository.findAll().forEach(book -> System.out.println(book));
    }

    @Test
    public void testGet() {
        System.out.println(bookRepository.findById(5L).get());

    }

    @Test
    public void testUpdate() {
        Book book = bookRepository.findById(1L).get();
        book.setPrice(25900);
        bookRepository.save(book);
    }

    @Test
    public void testRemove() {
        bookRepository.deleteById(20L);
    }
}
