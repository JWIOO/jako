package me.jiwoosong.springbootdeveloper.controller

import com.fasterxml.jackson.databind.ObjectMapper
import me.jiwoosong.springbootdeveloper.domain.Article
import me.jiwoosong.springbootdeveloper.dto.AddArticleRequest
import me.jiwoosong.springbootdeveloper.dto.UpdateArticleRequest
import me.jiwoosong.springbootdeveloper.repository.BlogRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
@AutoConfigureMockMvc
internal class BlogApiControllerTest {
    @Autowired
    protected var mockMvc: MockMvc? = null

    @Autowired
    protected var objectMapper: ObjectMapper? = null

    @Autowired
    private val context: WebApplicationContext? = null

    @Autowired
    var blogRepository: BlogRepository? = null

    @BeforeEach
    fun mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
            .build()
        blogRepository!!.deleteAll()
    }

    @DisplayName("addArticle: 블로그 글 추가에 성공한다.")
    @Test
    @kotlin.Throws(
        Exception::class
    )
    fun addArticle() {
        // given
        val url = "/api/articles"
        val title = "title"
        val content = "content"
        val userRequest = AddArticleRequest(title, content)

        val requestBody = objectMapper!!.writeValueAsString(userRequest)

        // when
        val result = mockMvc!!.perform(
            MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody)
        )


        // then
        result.andExpect(MockMvcResultMatchers.status().isCreated())
        val articles = blogRepository!!.findAll()
        Assertions.assertThat(articles.size).isEqualTo(1)
        assertThat(articles[0].getTitle()).isEqualTo(title)
        assertThat(articles[0].getContent()).isEqualTo(content)
    }

    @DisplayName("findAllArticles: 블로그 글 목록 조회에 성공한다.")
    @Test
    @kotlin.Throws(
        Exception::class
    )
    fun findAllArticles() {
        // given
        val url = "/api/articles"
        val title = "title"
        val content = "content"
        blogRepository!!.save(
            Article.builder()
                .title(title)
                .content(content)
                .build()
        )

        // when
        val resultActions = mockMvc!!.perform(
            MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON)
        )

        // then
        resultActions
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].content").value(content))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value(title))
    }


    @DisplayName("findArticle: 블로그 글 조회에 성공한다.")
    @Test
    @kotlin.Throws(
        Exception::class
    )
    fun findArticle() {
        // given
        val url = "/api/articles/{id}"
        val title = "title"
        val content = "content"
        val savedArticle: Article = blogRepository!!.save(
            Article.builder()
                .title(title)
                .content(content)
                .build()
        )

        // when
        val resultActions = mockMvc!!.perform(
            get(
                url, savedArticle.getId
                    ()
            )
        )

        // then
        resultActions
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.content").value(content))
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(title))
    }

    @DisplayName("deleteArticle: 블로그 글 삭제에 성공한다.")
    @Test
    @kotlin.Throws(
        Exception::class
    )
    fun deleteArticle() {
        // given
        val url = "/api/articles/{id}"
        val title = "title"
        val content = "content"
        val savedArticle: Article = blogRepository!!.save(
            Article.builder()
                .title(title)
                .content(content)
                .build()
        )

        // when
        mockMvc!!.perform(delete(url, savedArticle.getId()))
            .andExpect(MockMvcResultMatchers.status().isOk())

        // then
        val articles = blogRepository!!.findAll()
        Assertions.assertThat(articles).isEmpty()
    }

    @DisplayName("updateArticle: 블로그 글 수정에 성공한다.")
    @Test
    @kotlin.Throws(
        Exception::class
    )
    fun updateArticle() {
        // given
        val url = "/api/articles/{id}"
        val title = "title"
        val content = "content"
        val savedArticle: Article = blogRepository!!.save(
            Article.builder()
                .title(title)
                .content(content)
                .build()
        )
        val newTitle = "new title"
        val newContent = "new content"
        val request = UpdateArticleRequest(
            newTitle,
            newContent
        )

        // when
        val result = mockMvc!!.perform(
            put(url, savedArticle.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper!!.writeValueAsString(request))
        )

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk())
        val article = blogRepository!!.findById(savedArticle.getId()).get()
        assertThat(article.getTitle()).isEqualTo(newTitle)
        assertThat(article.getContent()).isEqualTo(newContent)
    }
}