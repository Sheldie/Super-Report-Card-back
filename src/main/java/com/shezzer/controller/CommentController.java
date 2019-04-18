package com.shezzer.controller;

import com.shezzer.pojo.Article;
import com.shezzer.pojo.Comment;
import com.shezzer.pojo.User;
import com.shezzer.pojo.base.Result;
import com.shezzer.service.ArticleService;
import com.shezzer.service.CommentService;
import com.shezzer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;

    @PostMapping("/addComment")
    public Result addComment(int COMMENT_USER, int ARTICLE_ID, String COMMENT_TEXT){
        try {
            User user = userService.findUserById(COMMENT_USER);
            Article article = articleService.findArticleById(ARTICLE_ID);
            if(user == null)
                return Result.failed(1, "User doesn't exist.");
            if(article == null)
                return Result.failed(2, "Article doesn't exist.");

            Comment comment = new Comment();
            comment.setCOMMENT_USER(COMMENT_USER);
            comment.setARTICLE_ID(ARTICLE_ID);
            comment.setCOMMENT_TEXT(COMMENT_TEXT);
            commentService.addComment(comment);
            return Result.success("Success");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/updateComment")
    public Result updateComment(int COMMENT_ID, String COMMENT_TEXT){
        try {
            Comment comment = commentService.findCommentById(COMMENT_ID);
            if(comment == null)
                return Result.failed(1, "Comment doesn't exist.");
            comment.setCOMMENT_TEXT(COMMENT_TEXT);
            commentService.updateComment(comment);
            return Result.success("Success");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/deleteComment")
    public Result deleteComment(int COMMENT_ID){
        try {
            Comment comment = commentService.findCommentById(COMMENT_ID);
            if(comment == null)
                return Result.failed(1, "Comment doesn't exist.");
            commentService.deleteComment(COMMENT_ID);
            return Result.success("Success");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/findCommentByArticle")
    public Result findCommentByArticle(int ARTICLE_ID){
        try{
            Article article = articleService.findArticleById(ARTICLE_ID);
            if(article == null)
                return Result.failed(1, "Article doesn't exist.");
            return Result.success(commentService.findCommentByArticle(ARTICLE_ID));
        }
        catch (Exception e) {
            return Result.error(e.toString());
        }
    }

    @PostMapping("/findCommentById")
    public Result findCommentById(int COMMENT_ID){
        try{
            Comment comment = commentService.findCommentById(COMMENT_ID);
            if(comment == null)
                return Result.failed(1, "Comment doesn't exist.");
            return Result.success(comment);
        }
        catch (Exception e) {
            return Result.error(e.toString());
        }
    }
}
