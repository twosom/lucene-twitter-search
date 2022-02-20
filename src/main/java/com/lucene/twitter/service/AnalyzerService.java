package com.lucene.twitter.service;

import com.lucene.twitter.model.TweetPost;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;

public class AnalyzerService {

    public void analyzeText(List<TweetPost> reviewList, Analyzer analyzer) {
        System.out.println("\n\n\n###################" + analyzer.getClass().getName() + " Test Result #############################################################");
        for (TweetPost tweetPost : reviewList) {
            if (StringUtils.hasText(tweetPost.getText())) {
                // TokenStream 을 생성한다.
                try (TokenStream tokenStream = analyzer.tokenStream("twitterText", tweetPost.getText())) {

                    // Token String 을 가져오기 위해 CharTermAttribute 설정
                    CharTermAttribute cta = tokenStream.addAttribute(CharTermAttribute.class);
                    // 스트림의 시작을 리셋한다. (필수 과정)
                    tokenStream.reset();
                    System.out.println();
                    // 토큰을 순차적으로 읽는다.
                    while (tokenStream.incrementToken()) {
                        // 토큰을 String Value 로 표시한다.
                        System.out.print(cta);
                        System.out.print(" | ");
                    }
                    // 사용을 마친 TokenStream 을 종료 처리한다.
                    // end 처리된 TokenStream 은 다른 토크나이저나 토큰 필더에서 재사용할 수 있다.
                    tokenStream.end();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
