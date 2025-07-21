package gpt01.gptrecommendation01.dto

import gpt01.gptrecommendation01.domain.UserEntity

data class SignUpDto (
    var name: String? = null,
    var email: String? = null,
    var password: String? = null,
){
    fun toEntity() : UserEntity{
        return UserEntity(
            name = name!!,
            email = email!!,
            password = password!!
        )
    }
}