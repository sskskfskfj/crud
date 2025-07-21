package gpt01.gptrecommendation01.domain

import jakarta.persistence.*
import java.time.LocalDateTime
import javax.security.auth.callback.LanguageCallback

@Entity
class PostEntity (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L,

    @Column(name = "title")
    val title : String = "",

    @Column(name = "post")
    val post : String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user : UserEntity,

    @Column(name = "created_at")
    val created_at : LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    val modified_at : LocalDateTime = LocalDateTime.now(),

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    val comment : MutableList<CommentEntity> = mutableListOf(),
)