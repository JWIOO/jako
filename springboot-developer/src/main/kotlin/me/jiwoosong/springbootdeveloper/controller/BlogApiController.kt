package me.jiwoosong.springbootdeveloper.controller

import lombok.RequiredArgsConstructor
import me.jiwoosong.springbootdeveloper.domain.Article
import me.jiwoosong.springbootdeveloper.dto.AddArticleRequest
import me.jiwoosong.springbootdeveloper.dto.ArticleResponse
import me.jiwoosong.springbootdeveloper.dto.UpdateArticleRequest
import me.jiwoosong.springbootdeveloper.service.BlogService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequiredArgsConstructor
@RestController
class BlogApiController {
    private val blogService: BlogService? = null
    @PostMapping("/api/articles")
    fun addArticle(@RequestBody request: AddArticleRequest?): ResponseEntity<Article> {
        val savedArticle = blogService!!.save(request)
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(savedArticle)
    }

    @GetMapping("/api/articles")
    fun findAllArticles(): ResponseEntity<List<ArticleResponse>> {
        val articles = blogService!!.findAll()
            .stream()
            .map { article: Article? ->
                ArticleResponse(
                    article
                )
            }
            .toList()
        return ResponseEntity.ok()
            .body(articles)
    }

    @GetMapping("/api/articles/{id}")
    fun findArticle(@PathVariable id: Long): ResponseEntity<ArticleResponse> {
        val article = blogService!!.findById(id)
        return ResponseEntity.ok()
            .body(ArticleResponse(article))
    }

    @DeleteMapping("/api/articles/{id}")
    fun deleteArticle(@PathVariable id: Long): ResponseEntity<Void> {
        blogService!!.delete(id)
        return ResponseEntity.ok()
            .build()
    }

    @PutMapping("/api/articles/{id}")
    fun updateArticle(
        @PathVariable id: Long,
        @RequestBody request: UpdateArticleRequest?
    ): ResponseEntity<Article> {
        val updatedArticle = blogService!!.update(id, request)
        return ResponseEntity.ok()
            .body(updatedArticle)
    }
}