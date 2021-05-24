package com.zzy.blog.po;

import javax.validation.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "t_type")
@Table
public class Type {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Don't use empty name")
    private String name;

    /*If the relationship is bidirectional,
     the mappedBy element must be used to specify
     the relationship field or property of the entity that is the owner of the relationship.* */
    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();

    public Type() {
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

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
