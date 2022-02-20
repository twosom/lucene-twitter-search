package com.lucene.twitter.example;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;

import java.io.IOException;

import static java.util.Arrays.asList;
import static org.apache.lucene.analysis.CharArraySet.unmodifiableSet;

public class StopFilterPositionIncrementAttributeExample {
    public static void main(String[] args) {
        String s = "blue is the sky";

        // 최신 lucene 에서는 StandardAnalyzer 에서 stopWords 가 빠져있기 때문에 직접 설정
        var stopWords = getStopWords();
        // StandardAnalyzer 생성
        var analyzer = new StandardAnalyzer(stopWords);

        // TokenStream 생성
        try (var tokenStream = analyzer.tokenStream("string", s)) {

            // 토큰의 값을 확인하기 위한 CharTermAttribute 설정
            var cta = tokenStream.addAttribute(CharTermAttribute.class);

            // PositionIncrement 를 확인하기 위한 PositionIncrementAttribute 설정
            var pia = tokenStream.addAttribute(PositionIncrementAttribute.class);

            // TokenStream 초기화
            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                System.out.println("CharTerm: " + cta + " | " + "PositionIncrement : " + pia.getPositionIncrement());
            }
            // TokenStream 종료
            tokenStream.end();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static CharArraySet getStopWords() {
        return unmodifiableSet(new CharArraySet(asList(
                "a", "an", "and", "are", "as", "at", "be", "but", "by",
                "for", "if", "in", "into", "is", "it",
                "no", "not", "of", "on", "or", "such",
                "that", "the", "their", "then", "there", "these",
                "they", "this", "to", "was", "will", "with"
        ), true));
    }
}
