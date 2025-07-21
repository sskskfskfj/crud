package gpt01.gptrecommendation01.service

import gpt01.gptrecommendation01.domain.PostEntity
import gpt01.gptrecommendation01.dto.ResponseDto
import gpt01.gptrecommendation01.dto.WriteDto
import gpt01.gptrecommendation01.repository.CommentRepository
import gpt01.gptrecommendation01.repository.PostRepository
import gpt01.gptrecommendation01.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService (
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val commentRepository: CommentRepository,
){
    private val logger = LoggerFactory.getLogger(javaClass)

    @Transactional
    fun create(request : WriteDto){
        val post = PostEntity(
            user = userRepository.findByName(request.username!!) ?: throw RuntimeException("user does not exist"),
            post = request.content!!,
            title = request.title!!,
        )
        postRepository.save(post)
    }

    @Transactional(readOnly = true)
    fun readAllPosts() : MutableList<PostEntity> {
        val posts : MutableList<PostEntity> = postRepository.findAll()
        return posts
    }

    @Transactional(readOnly = true)
    fun readSinglePost(id : Long) : ResponseDto<PostEntity?> {
        return if(isPostExist(id)){
            ResponseDto(
                code = 200,
                message = "Post found",
                data = postRepository.findById(id).get()
            )
        }else{
            ResponseDto(404, "Post not found", null)
        }
    }

    @Transactional
    fun deletePost(id : Long){
        commentRepository.deleteAllByPostId(id)
        if(isPostExist(id)){
            postRepository.deleteById(id)
        }else{
            throw RuntimeException("Post not found")
        }
    }

    fun isPostExist(id : Long) : Boolean = postRepository.findById(id).isPresent
}