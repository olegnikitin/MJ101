package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;

/**
 * Created by olegnikitindev on 14.08.2016.
 */
public class MongoStart {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("test").withReadPreference(ReadPreference.secondary());
        MongoCollection<BsonDocument> collection = database.getCollection("test", BsonDocument.class);
    }

}
