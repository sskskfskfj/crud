package gpt01.gptrecommendation01.controller

import gpt01.gptrecommendation01.dto.CustomUserDetails
import gpt01.gptrecommendation01.dto.WriteDto
import gpt01.gptrecommendation01.service.CommentService
import org.slf4j.LoggerFactory
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/comment")
class CommentController (
    private val commentService: CommentService,
){
    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping("/write/{id}")
    fun createComment(
        @AuthenticationPrincipal principal: CustomUserDetails,
        @ModelAttribute("request") request : WriteDto,
        @PathVariable("id") postId : Long,
    ) : String{
        logger.info("post id : $postId")
        commentService.addComment(principal, request, postId)

        return "redirect:/post/read/$postId"
    }

    @GetMapping("/delete/{postId}/{commentId}")
    fun deleteComment(
        @AuthenticationPrincipal principal: CustomUserDetails,
        @PathVariable("postId") postId : Long,
        @PathVariable("commentId") commentId: Long,
    ) : String{
        commentService.deleteComment(commentId)
        return "redirect:/post/read/$postId"
    }
}