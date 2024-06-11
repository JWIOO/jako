package me.jiwoosong.springbootdeveloper.repository;

import me.jiwoosong.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}