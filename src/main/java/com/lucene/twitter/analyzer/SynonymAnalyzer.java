package com.lucene.twitter.analyzer;

import lombok.RequiredArgsConstructor;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.FlattenGraphFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.synonym.SynonymGraphFilter;
import org.apache.lucene.analysis.synonym.SynonymMap;

@RequiredArgsConstructor
public class SynonymAnalyzer extends Analyzer {
    private final SynonymMap synonymMap;


    /**
     * 기존 SynonymFilter 가 deprecated 되어 SynonymGraphFilter 를 대체로 사용해야 한다.<br/>
     * 변경 방식으로는 SynonymFilter 는 index 타임과 search 타임 모두 사용할 수 있었으나, <br/>
     * SynonymGraphFilter 는 search 타임에만 사용할 수 있고 index 타임에 사용하려면 FlattenGraphFilter 를 사용해야 한다.
     */
    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        // Tokenizer 는 StandardTokenizer 사용
        var standardTokenizer = new StandardTokenizer();
        var flattenGraphFilter = new FlattenGraphFilter(standardTokenizer);

        // 색인 설정 시 미리 설정한 SynonymMap 으로 동의어를 포함한 문서를 찾는다.
        var synonymGraphFilter = new SynonymGraphFilter(flattenGraphFilter, this.synonymMap, true);
        return new TokenStreamComponents(standardTokenizer, synonymGraphFilter);

    }
}
