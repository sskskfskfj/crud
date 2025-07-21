package gpt01.gptrecommendation01.repository

import gpt01.gptrecommendation01.domain.PostEntity
import gpt01.gptrecommendation01.domain.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PostRepository : JpaRepository<PostEntity, Long> {
    fun findByUser(user : UserEntity) : MutableList<PostEntity>
    override fun findById(id: Long): Optional<PostEntity>
}