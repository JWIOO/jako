package me.jiwoosong.springbootdeveloper.dto

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import me.jiwoosong.springbootdeveloper.domain.Article

@NoArgsConstructor
@AllArgsConstructor
@Getter
class AddArticleRequest {
    private val title: String? = null
    private val content: String? = null
    fun toEntity(): Article {
        return Article.builder()
            .title(title)
            .content(content)
            .build()
    }
}