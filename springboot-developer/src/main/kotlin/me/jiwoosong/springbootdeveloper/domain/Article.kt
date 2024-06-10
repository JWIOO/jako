package me.jiwoosong.springbootdeveloper.domain

import jakarta.persistence.*
import lombok.AccessLevel
import lombok.Builder
import lombok.Getter
import lombok.NoArgsConstructor
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@EntityListeners(AuditingEntityListener::class)
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Article @Builder constructor(
    @field:Column(
        name = "title",
        nullable = false
    ) private var title: String, @field:Column(
        name = "content",
        nullable = false
    ) private var content: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private var id: Long? = null

    @CreatedDate
    @Column(name = "created_at")
    private var createdAt: LocalDateTime? = null

    @LastModifiedDate
    @Column(name = "updated_at")
    private var updatedAt: LocalDateTime? = null

    fun update(title: String, content: String) {
        this.title = title
        this.content = content
    }
}