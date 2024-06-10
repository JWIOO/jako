package me.jiwoosong.springbootdeveloper.dto

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor

@NoArgsConstructor
@AllArgsConstructor
@Getter
class UpdateArticleRequest {
    private val title: String? = null
    private val content: String? = null
}