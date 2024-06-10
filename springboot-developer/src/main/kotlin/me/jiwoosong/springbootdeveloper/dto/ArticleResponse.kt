package me.jiwoosong.springbootdeveloper.dto

import lombok.Getter
import me.jiwoosong.springbootdeveloper.domain.Article

@Getter
class ArticleResponse(article: Article) {
    private val title: String
    private val content: String

    init {
        this.title = article.getTitle()
        this.content = article.getContent()
    }
}