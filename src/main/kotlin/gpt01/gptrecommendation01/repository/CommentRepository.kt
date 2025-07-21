package gpt01.gptrecommendation01.repository

import gpt01.gptrecommendation01.domain.CommentEntity
import gpt01.gptrecommendation01.domain.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<CommentEntity, Long> {
    fun findByUser(user : UserEntity) : MutableList<CommentEntity>
    fun findAllByPostId(postId : Long) : MutableList<CommentEntity>
    fun deleteAllByPostId(postId : Long)
}