package gpt01.gptrecommendation01.service

import gpt01.gptrecommendation01.domain.UserEntity
import gpt01.gptrecommendation01.dto.CustomUserDetails
import gpt01.gptrecommendation01.dto.ResponseDto
import gpt01.gptrecommendation01.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional



@Service
class AuthService (
    private val userRepository : UserRepository,
    private val passwordEncoder : PasswordEncoder
){
    @Transactional
    fun signup(user : UserEntity) : ResponseDto<Any>{
        user.password = passwordEncoder.encode(user.password)

        userRepository.findByName(user.name)?.let {
            return ResponseDto(400, "user already exists", "")
        }
        userRepository.save(user)

        return ResponseDto(200, "Success", "")
    }
}