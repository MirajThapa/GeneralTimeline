package com.example.timeline;

public class Post {
    // PRIVATE VARIABLES DECLARED
    private String postId;
    private String userId;
    private String description;
    private String date;
    private String name;

    // CONSTRUCTOR USED IN DATABASE
    public Post(String postId,String userId,String name,  String description, String date) {
        this.postId = postId;
        this.name=name;
        this.userId = userId;
        this.description = description;
        this.date = date;
    }

    // CONSTRUCTOR USED IN ADD POST SECTION
    public Post(String name, String userId, String description, String date) {
        this.name=name;
        this.userId = userId;
        this.description = description;
        this.date = date;
    }

    // CONSTRUCTOR USED TO SHOW POSTS IN TIMELINE SECTION
    public Post(String name, String description, String date) {
        this.name=name;
        this.description = description;
        this.date = date;
    }

    // POST USED TO DELETE THE POST
    public Post(String userId,String name) {
        this.userId = userId;
        this.name=name;
    }


    // GETTERS AND SETTERS OF THE VARIABLES TO ACCESS THEM THROUGH OTHER CLASS

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
