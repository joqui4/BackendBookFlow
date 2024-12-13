package com.BookFlow.usuarios.clubs.domain.model.commands;

public class PopulateCommentCommand {
    private final Long commentId;
    private final String content;

    public PopulateCommentCommand(Long commentId, String content) {
        this.commentId = commentId;
        this.content = content;
    }
    public Long getCommentId() {
        return commentId;
    }
    public String getContent() {
        return content;
    }
}
