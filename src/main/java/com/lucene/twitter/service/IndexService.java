package com.lucene.twitter.service;

import com.lucene.twitter.model.TweetPost;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;

import java.io.IOException;
import java.util.List;

public class IndexService {
    public static void indexingTweetData(List<TweetPost> reviewList, Directory directory, IndexWriterConfig config) {
        try (IndexWriter writer = new IndexWriter(directory, config)) {
            reviewList.forEach(i -> addDocument(i, writer));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addDocument(TweetPost review, IndexWriter writer) {
        Document doc = new Document();
        doc.add(new TextField("createdAt", review.getCreatedAt(), Field.Store.YES));
        doc.add(new StringField("id", review.getId(), Field.Store.YES));
        doc.add(new StringField("lang", review.getLang(), Field.Store.YES));
        doc.add(new TextField("text", review.getText(), Field.Store.YES));
        try {
            writer.addDocument(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
