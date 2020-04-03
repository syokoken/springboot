package com.example.demo.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Message {
    @NotEmpty(message = "id不能为空")
    private Long id;
    private String text;
    private String summary;
}
