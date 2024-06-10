package me.jiwoosong.springbootdeveloper.dto

import lombok.Getter
import me.jiwoosong.springbootdeveloper.domain.Article

@Getter
class ArticleListViewResponse(article: Article) {
    private val id: Long
    private val title: String
    private val content: String

    init {
        this.id = article.getId()
        this.title = article.getTitle()
        this.content = article.getContent()
    }
}