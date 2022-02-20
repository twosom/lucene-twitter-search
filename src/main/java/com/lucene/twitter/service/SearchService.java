package com.lucene.twitter.service;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;

import java.io.IOException;

public class SearchService {

    private static final int HITS_PER_PAGE = 10;

    public static void searchTweetData(Directory directory, Query query) {

        // 검색을 위한 Reader 구성
        try (IndexReader reader = DirectoryReader.open(directory)) {
            IndexSearcher indexSearcher = new IndexSearcher(reader);
            TopDocs docs = indexSearcher.search(query, HITS_PER_PAGE);
            ScoreDoc[] hits = docs.scoreDocs;

            System.out.println("Found " + hits.length + " hits.");
            for (ScoreDoc hit : hits) {
                int docId = hit.doc;
                Document doc = indexSearcher.doc(docId);
                System.out.println("반응 = " + doc.get("text"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
