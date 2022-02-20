package com.lucene.twitter.example;

import com.lucene.twitter.model.TweetPost;
import com.lucene.twitter.service.IndexService;
import com.lucene.twitter.service.SearchService;
import com.lucene.twitter.util.CsvLoader;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.ko.KoreanAnalyzer;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.ByteBuffersDirectory;
import org.apache.lucene.store.Directory;

import java.util.List;

public class NoriKoreanAnalyzerExample {

    public static void main(String[] args) throws ParseException {
        CsvLoader csvLoader = new CsvLoader();
        List<TweetPost> reviewList = csvLoader.readKoreanReview();

        // 색인을 위해 디렉토리 생성
        Directory directory = new ByteBuffersDirectory();
        // 노리 한글 형태소 분석기를 설정한다.
        Analyzer analyzer = new KoreanAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        // 트위터 날씨 반응 데이터를 색인한다.
        IndexService.indexingTweetData(reviewList, directory, config);

        // 먼지로 쿼리를 생성한다.
        Query query = new QueryParser("text", analyzer).parse("먼지");
        // 쿼리로 검색하고 결과를 확인한다.
        SearchService.searchTweetData(directory, query);
    }
}
