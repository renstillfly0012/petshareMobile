package com.example.petshare;

import android.content.Context;
import android.widget.Toast;

public class viewallPet {

    Integer id;
    String name;
    String image;
    Integer age;
    String breed;
    String status;
    String description;
    String createdAt;
    String updatedAt;

    public viewallPet(Integer id, String name, String image, Integer age, String breed, String status, String description) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.age = age;
        this.breed = breed;
        this.status = status;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void showToast(String message, Context context){
        Toast.makeText(context,
                message,
                Toast.LENGTH_SHORT).show();
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
