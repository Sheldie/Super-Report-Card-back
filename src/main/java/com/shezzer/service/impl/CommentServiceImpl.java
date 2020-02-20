package com.shezzer.service.impl;

import com.shezzer.mapper.CommentMapper;
import com.shezzer.pojo.Comment;
import com.shezzer.pojo.result.CommentMap;
import com.shezzer.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public void addComment(Comment comment) {
        commentMapper.addComment(comment);
    }

    @Override
    public void updateComment(Comment comment) {
        commentMapper.updateComment(comment);
    }

    @Override
    public void deleteComment(int COMMENT_ID) {
        commentMapper.deleteComment(COMMENT_ID);
    }

    @Override
    public Comment findCommentById(int COMMENT_ID) {
        return commentMapper.findCommentById(COMMENT_ID);
    }

    @Override
    public List<CommentMap> findCommentByArticle(int ARTICLE_ID) {
        return commentMapper.findCommentByArticle(ARTICLE_ID);
    }
}
