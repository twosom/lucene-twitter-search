package com.lucene.twitter.example;

import com.lucene.twitter.model.TweetPost;
import com.lucene.twitter.service.AnalyzerService;
import com.lucene.twitter.util.CsvLoader;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

import java.util.List;

import static com.lucene.twitter.example.StopFilterPositionIncrementAttributeExample.getStopWords;

public class TwitterTextAnalyzerMain {

    public static void main(String[] args) {
        CsvLoader csvLoader = new CsvLoader();
        List<TweetPost> reviewList = csvLoader.readEnglishReview();

        AnalyzerService analyzerService = new AnalyzerService();

        analyzerService.analyzeText(reviewList, new StandardAnalyzer());

        analyzerService.analyzeText(reviewList, new SimpleAnalyzer());

        analyzerService.analyzeText(reviewList, new WhitespaceAnalyzer());

        analyzerService.analyzeText(reviewList, new StopAnalyzer(getStopWords()));

        analyzerService.analyzeText(reviewList, new KeywordAnalyzer());

    }
}
