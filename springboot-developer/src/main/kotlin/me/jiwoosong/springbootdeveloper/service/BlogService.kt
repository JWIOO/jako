package me.jiwoosong.springbootdeveloper.service

import lombok.RequiredArgsConstructor
import me.jiwoosong.springbootdeveloper.domain.Article
import me.jiwoosong.springbootdeveloper.dto.AddArticleRequest
import me.jiwoosong.springbootdeveloper.dto.UpdateArticleRequest
import me.jiwoosong.springbootdeveloper.repository.BlogRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@RequiredArgsConstructor
@Service
class BlogService {
    private val blogRepository: BlogRepository? = null

    fun save(request: AddArticleRequest): Article {
        return blogRepository!!.save(request.toEntity())
    }

    fun findAll(): List<Article?> {
        return blogRepository!!.findAll()
    }

    fun findById(id: Long): Article {
        return blogRepository!!.findById(id)
            .orElseThrow {
                IllegalArgumentException(
                    "not found: $id"
                )
            }!!
    }

    fun delete(id: Long) {
        blogRepository!!.deleteById(id)
    }

    @Transactional
    fun update(id: Long, request: UpdateArticleRequest): Article {
        val article = blogRepository!!.findById(id)
            .orElseThrow {
                IllegalArgumentException(
                    "not found: $id"
                )
            }!!
        article.update(request.getTitle(), request.getContent())
        return article
    }
}