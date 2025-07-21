package gpt01.gptrecommendation01.controller

import gpt01.gptrecommendation01.dto.CustomUserDetails
import gpt01.gptrecommendation01.dto.WriteDto
import gpt01.gptrecommendation01.service.CommentService
import gpt01.gptrecommendation01.service.PostService
import org.slf4j.LoggerFactory
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*


@Controller
@RequestMapping("/post")
class PostController (
    private val postService : PostService,
    private val commentService: CommentService,
){
    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun postPage() : String {
        return "writePost"
    }

    @PostMapping("/write")
    fun createPost(
        @AuthenticationPrincipal principal: CustomUserDetails,
        @ModelAttribute request : WriteDto,
    ) : String{
        request.username = principal.username
        logger.info("username : ${request.username}")

        postService.create(request)
        return "redirect:/main"
    }

    @GetMapping("/read/{id}")
    fun getPost(
        @AuthenticationPrincipal principal: CustomUserDetails?,
        @PathVariable("id") postId: Long,
        model : Model,
    ) : String{
        logger.info("post id : $postId")
        val post = postService.readSinglePost(postId).data

        model.addAttribute("comments", commentService.getAllComments(postId))
        model.addAttribute("post", post)
        model.addAttribute("user", principal)
        return "singlePost"
    }

    @GetMapping("/delete/{id}")
    fun deletePost(
        @AuthenticationPrincipal principal: CustomUserDetails,
        @PathVariable("id") id: Long,
    ) : String{
        postService.deletePost(id)
        return "redirect:/main"
    }
}