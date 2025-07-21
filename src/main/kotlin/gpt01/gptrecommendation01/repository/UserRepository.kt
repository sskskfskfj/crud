package gpt01.gptrecommendation01.repository

import gpt01.gptrecommendation01.domain.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByName(name: String): UserEntity?
}