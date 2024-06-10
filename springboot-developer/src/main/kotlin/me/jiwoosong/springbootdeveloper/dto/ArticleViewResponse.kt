package me.jiwoosong.springbootdeveloper.dto

import lombok.Getter
import lombok.NoArgsConstructor
import me.jiwoosong.springbootdeveloper.domain.Article
import java.time.LocalDateTime

@NoArgsConstructor
@Getter
class ArticleViewResponse(article: Article) {
    private val id: Long
    private val title: String
    private val content: String
    private val createdAt: LocalDateTime

    init {
        this.id = article.getId()
        this.title = article.getTitle()
        this.content = article.getContent()
        this.createdAt = article.getCreatedAt()
    }
}