package com.lucene.twitter.example;

import com.lucene.twitter.analyzer.SynonymAnalyzer;
import com.lucene.twitter.model.TweetPost;
import com.lucene.twitter.service.IndexService;
import com.lucene.twitter.service.SearchService;
import com.lucene.twitter.util.CsvLoader;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.synonym.SynonymMap;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.ByteBuffersDirectory;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.CharsRef;

import java.util.List;

public class SynonymIndexTimeExample {
    public static void main(String[] args) throws Exception {
        CsvLoader csvLoader = new CsvLoader();
        List<TweetPost> reviewList = csvLoader.readEnglishReview();
        reviewList.forEach(System.out::println);

        Directory directory = new ByteBuffersDirectory();
        SynonymMap.Builder builder = new SynonymMap.Builder(true);
        builder.add(new CharsRef("good"), new CharsRef("clear"), true);

        var analyzer = new SynonymAnalyzer(builder.build());
        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        IndexService.indexingTweetData(reviewList, directory, config);

        Query query = new QueryParser("text", new StandardAnalyzer()).parse("clear");

        SearchService.searchTweetData(directory, query);
    }
}
