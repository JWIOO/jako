package me.jiwoosong.springbootdeveloper.controller

import lombok.RequiredArgsConstructor
import me.jiwoosong.springbootdeveloper.domain.Article
import me.jiwoosong.springbootdeveloper.dto.ArticleListViewResponse
import me.jiwoosong.springbootdeveloper.service.BlogService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@RequiredArgsConstructor
@Controller
class BlogViewController {
    private val blogService: BlogService? = null
    @GetMapping("/articles")
    fun getArticles(model: Model): String {
        val articles = blogService!!.findAll().stream()
            .map { article: Article? ->
                ArticleListViewResponse(
                    article
                )
            }
            .toList()

        model.addAttribute("articles", articles)

        return "articleList"
    }

    @GetMapping("/articles/{id}")
    fun getArticle(@PathVariable id: Long?, model: Model): String {
        val article = blogService!!.findById(id!!)
        model.addAttribute("article", ArticleViewResponse(article))
        return "article"
    }

    @GetMapping("/new-article")
    fun newArticle(@RequestParam(required = false) id: Long?, model: Model): String {
        if (id == null) {
            model.addAttribute("article", ArticleViewResponse())
        } else {
            val article = blogService!!.findById(id)
            model.addAttribute("article", ArticleViewResponse(article))
        }

        return "newArticle"
    }
}