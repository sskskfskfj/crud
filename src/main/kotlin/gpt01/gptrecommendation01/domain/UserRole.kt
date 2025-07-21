package gpt01.gptrecommendation01.domain

enum class UserRole(
    private val role : String
) {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    fun value() : String = this.role
}