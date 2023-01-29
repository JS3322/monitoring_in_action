package com.example.management_data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;
    private String name;
    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {
        NAVER, KAKAO, GMAIL
    }
}
