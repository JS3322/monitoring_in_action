package com.example.management_data.entity;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "scrapingtable")
@Entity
public class ScrapEntityExample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private int number;

    @Column(nullable=false)
    private String title;

    @Column(nullable=false)
    private double numberScore;

    @Column(nullable=false)
    private int numberScoreList;

    @Column(nullable=false)
    private int numberOfReview;

    @Column(length = 1000,nullable=false)
    private String viewMoreReviewDetail;


    public ScrapEntityExample(int number, String title, double numberScore, int numberScoreList, int numberOfReview, String viewMoreReviewDetail ) {
        this.number = number;
        this.title = title;
        this.numberScore = numberScore;
        this.numberScoreList =numberScoreList;
        this.numberOfReview = numberOfReview;
        this.viewMoreReviewDetail = viewMoreReviewDetail;
    }
}
