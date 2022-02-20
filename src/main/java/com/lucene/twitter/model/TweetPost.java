package com.lucene.twitter.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TweetPost {

    /**
     * 등록 일시
     */
    @CsvBindByPosition(position = 0)
    private String createdAt;

    /**
     * 트위터 글 아이디
     */
    @CsvBindByPosition(position = 1)
    private String id;

    /**
     * 언어 종류
     */
    @CsvBindByPosition(position = 2)
    private String lang;

    /**
     * 트위터 글
     */
    @CsvBindByPosition(position = 3)
    private String text;
}
