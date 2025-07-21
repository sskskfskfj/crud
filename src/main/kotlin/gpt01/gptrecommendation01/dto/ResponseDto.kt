package gpt01.gptrecommendation01.dto

class ResponseDto<T> (
    val code : Int,
    val message : String,
    val data : T,
)