package com.example.retrofitdemonew;

public class CommitBean {

    /**
     * articleId : 234123
     * commentContent : 这是评论内容
     */

    private String articleId;
    private String commentContent;

    public CommitBean() {
    }

    public CommitBean(String articleId, String commentContent) {
        this.articleId = articleId;
        this.commentContent = commentContent;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }


}
