package gpt01.gptrecommendation01.controller

import gpt01.gptrecommendation01.domain.PostEntity
import gpt01.gptrecommendation01.dto.CustomUserDetails
import gpt01.gptrecommendation01.service.PostService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController (
    private val postService : PostService,
){
    @GetMapping("/", "/main")
    fun main(
        @AuthenticationPrincipal principal: CustomUserDetails?,
        model : Model,
    ) : String {
        if (principal != null) {
            model.addAttribute("principal", principal.username)
        }else{
            model.addAttribute("principal", "not login")
        }
        model.addAttribute("posts", postService.readAllPosts())
        return "main"
    }
}