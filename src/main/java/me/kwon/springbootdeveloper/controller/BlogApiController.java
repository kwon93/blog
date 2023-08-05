package me.kwon.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.kwon.springbootdeveloper.domain.Article;
import me.kwon.springbootdeveloper.dto.AddArticleRequest;
import me.kwon.springbootdeveloper.dto.ArticleResponse;
import me.kwon.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticles(@RequestBody AddArticleRequest request){
        Article savedArticle = blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED) //요청 자원이 성공적으로 처리됨.
                .body(savedArticle); //저장된 블로그 글 정보를 응답 객체에 담아 전송.
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .collect(Collectors.toList());;

        return ResponseEntity.ok()
                .body(articles);
    }




}
