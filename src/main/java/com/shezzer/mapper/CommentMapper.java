package com.shezzer.mapper;

import com.shezzer.pojo.Comment;
import com.shezzer.pojo.result.CommentMap;

import java.util.List;

public interface CommentMapper {
    void addComment(Comment comment);
    void updateComment(Comment comment);
    void deleteComment(int COMMENT_ID);
    Comment findCommentById(int COMMENT_ID);
    List<CommentMap> findCommentByArticle(int ARTICLE_ID);
}
