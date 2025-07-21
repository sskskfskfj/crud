package gpt01.gptrecommendation01.service

import gpt01.gptrecommendation01.domain.CommentEntity
import gpt01.gptrecommendation01.dto.CustomUserDetails
import gpt01.gptrecommendation01.dto.WriteDto
import gpt01.gptrecommendation01.repository.CommentRepository
import gpt01.gptrecommendation01.repository.PostRepository
import gpt01.gptrecommendation01.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService (
    private val commentRepository: CommentRepository,
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
){
    private val logger = LoggerFactory.getLogger(javaClass)

    @Transactional
    fun addComment(
        principal : CustomUserDetails,
        request : WriteDto,
        postId : Long,
    ){
        logger.info("username : ${principal.username}")

        val post = postRepository.findById(postId).get()
        val comment = CommentEntity(
            user = userRepository.findByName(principal.username)!!,
            post = post,
            content = request.content!!
        )
        commentRepository.save(comment)
    }

    @Transactional(readOnly = true)
    fun getAllComments(postId : Long) : MutableList<CommentEntity>{
        return commentRepository.findAllByPostId(postId)
    }

    @Transactional
    fun deleteComment(commentId : Long){
        commentRepository.deleteById(commentId)
    }
}