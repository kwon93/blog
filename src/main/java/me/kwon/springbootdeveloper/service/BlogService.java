package me.kwon.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.kwon.springbootdeveloper.domain.Article;
import me.kwon.springbootdeveloper.dto.AddArticleRequest;
import me.kwon.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll(){
        return blogRepository.findAll();
    }
}
