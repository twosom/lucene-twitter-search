package com.lucene.twitter.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.ngram.NGramTokenFilter;
import org.apache.lucene.analysis.ngram.NGramTokenizer;

public class NgramAnalyzer extends Analyzer {

    public static final int MIN_GRAM = 2;
    public static final int MAX_GRAM = 2;

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        Tokenizer tokenizer = new NGramTokenizer(MIN_GRAM, MAX_GRAM);
        NGramTokenFilter nGramTokenFilter = new NGramTokenFilter(tokenizer, 1, 1, false);
        return new TokenStreamComponents(tokenizer, nGramTokenFilter);
    }
}
