package gpt01.gptrecommendation01.service

import gpt01.gptrecommendation01.domain.UserEntity
import gpt01.gptrecommendation01.dto.CustomUserDetails
import gpt01.gptrecommendation01.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CustomUserDetailsService(
    private val userRepository : UserRepository,
) : UserDetailsService{
    private val logger = LoggerFactory.getLogger(javaClass)

    @Transactional(readOnly = true)
    override fun loadUserByUsername(username: String?): CustomUserDetails? {
        val user : UserEntity = userRepository.findByName(username!!)
            ?: throw UsernameNotFoundException("User $username not found")

        logger.info("username : ${user.name}")
        logger.info("email : ${user.email}")

        return CustomUserDetails(user.name, user.email, user.password)
    }
}