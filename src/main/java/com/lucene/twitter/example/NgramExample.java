package com.lucene.twitter.example;

import com.lucene.twitter.analyzer.NgramAnalyzer;
import com.lucene.twitter.model.TweetPost;
import com.lucene.twitter.service.IndexService;
import com.lucene.twitter.service.SearchService;
import com.lucene.twitter.util.CsvLoader;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.ByteBuffersDirectory;
import org.apache.lucene.store.Directory;

import java.util.List;

public class NgramExample {
    public static void main(String[] args) throws ParseException {
        CsvLoader csvLoader = new CsvLoader();
        List<TweetPost> reviewList = csvLoader.readKoreanReview();

        // 색인을 위해 디렉토리 생성
        Directory directory = new ByteBuffersDirectory();
        // N-Gram 분석기를 설정
        IndexWriterConfig config = new IndexWriterConfig(new NgramAnalyzer());
        IndexService.indexingTweetData(reviewList, directory, config);

        // 먼지 검색 쿼리 생성
        Query query = new QueryParser("text", new NgramAnalyzer()).parse("먼지");
        SearchService.searchTweetData(directory, query);
    }
}
