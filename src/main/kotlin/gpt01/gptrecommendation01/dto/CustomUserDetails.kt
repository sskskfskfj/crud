package gpt01.gptrecommendation01.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class CustomUserDetails(
    private val username: String,
    private val email : String,
    private val password : String,
) : UserDetails{
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority("ROLE_USER"))
    }
    override fun getPassword(): String = password
    override fun getUsername(): String = username
    fun getEmail(): String = email
}