package com.example.UserTask.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetMessage {
    public String status;
    public String Message;
    public String path;
}