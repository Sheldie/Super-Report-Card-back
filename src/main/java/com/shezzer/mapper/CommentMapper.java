package com.shezzer.mapper;

import com.shezzer.pojo.Comment;

import java.util.List;

public interface CommentMapper {
    void addComment(Comment comment);
    void updateComment(Comment comment);
    void deleteComment(int COMMENT_ID);
    Comment findCommentById(int COMMENT_ID);
    List<Comment> findCommentByArticle(int ARTICLE_ID);
}
