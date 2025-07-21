package gpt01.gptrecommendation01.domain

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class CommentEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user : UserEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    val post : PostEntity,

    @Column(name = "content")
    val content : String = "",

    @Column(name = "created_at")
    val created_at : LocalDateTime = LocalDateTime.now(),

    @Column(name = "modified_at")
    val modified_at : LocalDateTime = LocalDateTime.now(),
){

}