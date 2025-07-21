package gpt01.gptrecommendation01.controller

import gpt01.gptrecommendation01.dto.LoginDto
import gpt01.gptrecommendation01.dto.SignUpDto
import gpt01.gptrecommendation01.dto.ResponseDto
import gpt01.gptrecommendation01.service.AuthService
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping



@Controller
@RequestMapping("/auth")
class AuthController (
    private val authService: AuthService,
){
    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/signup")
    fun signup(model : Model) : String{
        model.addAttribute("request", SignUpDto())
        return "signup"
    }

    @GetMapping("/login")
    fun login(model : Model) : String {
        model.addAttribute("request", LoginDto())
        return "login"
    }

    @PostMapping("/signup")
    fun signup(
        @ModelAttribute authRequest : SignUpDto
    ) : String {
        val result : ResponseDto<Any> = authService.signup(authRequest.toEntity())
        logger.info("sign up :  ${result.code} ${result.message}")
        return "redirect:/login"
    }

    @PostMapping("/login")
    fun login(
        @ModelAttribute authRequest : LoginDto,
        model : Model
    ) : String {
        logger.info("login : ${authRequest.username}")
        val user = authRequest.toEntity()
        val authentication = UsernamePasswordAuthenticationToken(user.name, user.password)

        SecurityContextHolder.getContext().authentication = authentication
        return "redirect:/main"
    }
}