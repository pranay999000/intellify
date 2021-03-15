package com.example.intellify;

public class Chat {
    String image;
    String name;
    String message;

    public Chat(String image, String name, String message) {
        this.image = image;
        this.name = name;
        this.message = message;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
