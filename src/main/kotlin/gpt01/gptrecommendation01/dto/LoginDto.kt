package gpt01.gptrecommendation01.dto

import gpt01.gptrecommendation01.domain.UserEntity

class LoginDto (
    var username : String? = null,
    var password : String? = null,
){
    fun toEntity() : UserEntity{
        return UserEntity(
            name = username!!,
            password = password!!,
        )
    }
}