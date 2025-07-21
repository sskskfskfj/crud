package gpt01.gptrecommendation01.domain

import jakarta.persistence.*

@Entity
class UserEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0L,

    @Column(name = "username", nullable = false)
    var name : String = "",

    @Column(name = "email", nullable = false)
    var email : String = "",

    @Column(name = "password", nullable = false)
    var password : String = "",

){
    @Column(name = "post")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var post: MutableList<PostEntity> = mutableListOf()

    @Column(name = "comment")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var comment : MutableList<CommentEntity> = mutableListOf()

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    var role : UserRole = UserRole.ROLE_USER

}