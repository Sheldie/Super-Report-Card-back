package com.shezzer.controller;

import com.shezzer.pojo.Article;
import com.shezzer.pojo.Comment;
import com.shezzer.pojo.User;
import com.shezzer.pojo.base.Result;
import com.shezzer.pojo.result.CommentMap;
import com.shezzer.service.ArticleService;
import com.shezzer.service.CommentService;
import com.shezzer.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;

    @PostMapping("/addArticle")
    public Result addArticle(String ARTICLE_TITLE, int ARTICLE_AUTHOR, String ARTICLE_TEXT){
        try{
            User user = userService.findUserById(ARTICLE_AUTHOR);
            if(user != null){
                int AY = user.getUSER_AUTHORITY();
                if(AY == 2){
                    Article article = new Article();
                    article.setARTICLE_TITLE(ARTICLE_TITLE);
                    article.setARTICLE_AUTHOR(ARTICLE_AUTHOR);
                    article.setARTICLE_TEXT(ARTICLE_TEXT);
                    articleService.addArticle(article);
                    return Result.success("Success");
                }
                else
                    return Result.failed(2, "Not a teacher.");
            }
            else
                return Result.failed(1, "User doesn't exist.");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/updateArticle")
    public Result updateArticle(int ARTICLE_ID, String ARTICLE_TITLE, String ARTICLE_TEXT){
        try{
            Article article = articleService.findArticleById(ARTICLE_ID);
            if(article != null){
                article.setARTICLE_TITLE(ARTICLE_TITLE);
                article.setARTICLE_TEXT(ARTICLE_TEXT);
                articleService.updateArticle(article);
                return Result.success("Success");
            }
            else
                return Result.failed(1, "Article doesn't exist.");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/deleteArticle")
    public Result deleteArticle(int ARTICLE_ID){
        try {
            Article article = articleService.findArticleById(ARTICLE_ID);
            if(article != null){
                List<CommentMap> list = commentService.findCommentByArticle(ARTICLE_ID);
                for(CommentMap ct : list)
                    commentService.deleteComment(ct.getCOMMENT_ID());
                articleService.deleteArticle(ARTICLE_ID);
                return Result.success("Success");
            }
            else
                return Result.failed(1, "Article doesn't exist.");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/findArticleById")
    @ApiOperation(value = "根据ID查找文章", notes = "")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Success"),
            @ApiResponse(code = 1, message = "Article doesn't exist.")
    })
    public Result findArticleById(int ARTICLE_ID){
        try{
            Article article = articleService.findArticleById(ARTICLE_ID);
            if(article != null)
                return Result.success(article);
            else
                return Result.failed(1, "Article doesn't exist.");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/listArticle")
    @ApiOperation(value = "文章列表", notes = "")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Success"),
            @ApiResponse(code = 1, message = "Article doesn't exist.")
    })
    public Result listArticle(){
        try{
            return Result.success(articleService.listArticle());
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }


}
