package com.example.management_data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ScrapDtoExample {

    private Long id;

    private int number;

    private String title;

    private double numberScore;

    private int numberScoreList;

    private int numberOfReview;

//    private String viewMoreReviewDetail;

    private Integer Analysis;
}
