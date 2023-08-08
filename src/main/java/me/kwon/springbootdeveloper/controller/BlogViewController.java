package me.kwon.springbootdeveloper.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.kwon.springbootdeveloper.domain.Article;
import me.kwon.springbootdeveloper.dto.ArticleListViewResponse;
import me.kwon.springbootdeveloper.dto.ArticleViewResponse;
import me.kwon.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model){
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .collect(Collectors.toList());
        model.addAttribute("articles",articles);

        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id,Model model){
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article)); //엔티티를 바로 넘기는게 아니라 dto를 통해 넘김.

        return "article";
    }

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model){
        if (id == null){
            model.addAttribute("article", new ArticleViewResponse());
        }else {
            Article article = blogService.findById(id);
            model.addAttribute("article",article);
        }

        return "newArticle";
    }
}
