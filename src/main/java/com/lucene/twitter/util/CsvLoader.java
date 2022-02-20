package com.lucene.twitter.util;

import com.lucene.twitter.model.TweetPost;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvLoader {

    public static void main(String[] args) {
        List<TweetPost> tweetPosts = new CsvLoader().readKoreanReview();
        System.out.println("tweetPosts = " + tweetPosts);
    }

    /**
     * 영어 트위터 게시글들을 가져옵니다.
     *
     * @return 영어 트위터 게시글 목록
     */
    public List<TweetPost> readEnglishReview() {
        try {
            String filePath = new ClassPathResource("tweet_en.csv").getFile().getAbsolutePath();
            return getPost(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 한글 트위터 게시글들을 가져옵니다.
     *
     * @return 한글 트위터 게시글 목록
     */
    public List<TweetPost> readKoreanReview() {
        try {
            String filePath = new ClassPathResource("tweet_ko.csv").getFile().getAbsolutePath();
            return getPost(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 파일 경로로 csv 파일을 불러오고 해당 csv 파일로 트위터 게시글 객체 목록으로 변환한 후 반환합니다.
     *
     * @param filePath 가져오려는 트위터 게시글 csv 파일이 저장 된 파일 경로
     * @return 변환 된 트위터 게시글 객체 목록
     */
    private List<TweetPost> getPost(String filePath) {
        List<TweetPost> reviewList = null;
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            var csvToBean = new CsvToBeanBuilder<TweetPost>(reader)
                    .withType(TweetPost.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSkipLines(1)
                    .build();
            reviewList = csvToBean.parse();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return reviewList;
    }
}
