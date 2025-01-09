package com.example.tvpssmis4.repository;

import com.example.tvpssmis4.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

