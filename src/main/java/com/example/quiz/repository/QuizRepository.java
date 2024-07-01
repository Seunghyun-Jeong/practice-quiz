package com.example.quiz.repository;

import com.example.quiz.entity.Quiz;
import org.springframework.data.repository.CrudRepository;

/** Quiz테이블: RepositoryImpl */
public interface QuizRepository extends CrudRepository<Quiz, Integer> {
}
