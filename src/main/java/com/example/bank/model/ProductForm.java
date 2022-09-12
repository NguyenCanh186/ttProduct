package com.example.bank.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

public class ProductForm {
    private Long id;
    private String name;
    private String description;
    private MultipartFile image;

    public ProductForm() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public ProductForm(ProductFormBuilder productFormBuilder) {
        this.name = productFormBuilder.name;
        this.description = productFormBuilder.description;
        this.image = productFormBuilder.image;
    }

    public static class ProductFormBuilder {
        private final String name;
        private String description;
        private MultipartFile image;

        public ProductFormBuilder(String name) {
            this.name = name;
        }

        public ProductFormBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProductFormBuilder image(MultipartFile image) {
            this.image = image;
            return this;
        }

        public ProductForm build() {
            return new ProductForm(this);
        }
    }
}
