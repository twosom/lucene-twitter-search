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

public class SynonymSearchTimeExample {

    public static void main(String[] args) throws Exception {
        CsvLoader csvLoader = new CsvLoader();
        List<TweetPost> reviewList = csvLoader.readEnglishReview();

        // 색인한다.
        Directory directory = new ByteBuffersDirectory();
        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
        IndexService.indexingTweetData(reviewList, directory, config);

        SynonymMap.Builder builder = new SynonymMap.Builder(true);
        builder.add(new CharsRef("nice"), new CharsRef("good"), true);

        Query query = new QueryParser("text", new SynonymAnalyzer(builder.build())).parse("nice");

        SearchService.searchTweetData(directory, query);
    }
}
