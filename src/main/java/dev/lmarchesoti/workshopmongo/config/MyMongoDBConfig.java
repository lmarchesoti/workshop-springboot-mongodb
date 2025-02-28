package dev.lmarchesoti.workshopmongo.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collections;

@Configuration
@EnableMongoRepositories(basePackages = { "dev.lmarchesoti.workshopmongo.repository" })
@EnableConfigurationProperties
public class MyMongoDBConfig {

    @Bean(name = "myMongoProperties")
    @ConfigurationProperties(prefix = "mongodb.mymongo")
    @Primary
    public MongoProperties myMongoProperties() {
        return new MongoProperties();
    }

    @Bean(name = "myMongoClient")
    public MongoClient mongoClient(@Qualifier("myMongoProperties") MongoProperties mongoProperties) {

        MongoCredential mongoCredential = MongoCredential.createCredential(mongoProperties.getUsername(),
                mongoProperties.getAuthenticationDatabase(), mongoProperties.getPassword());

        return MongoClients.create(MongoClientSettings.builder()
                        .applyToClusterSettings(builder -> builder
                                .hosts(Collections.singletonList(
                                        new ServerAddress(mongoProperties.getHost(), mongoProperties.getPort()))
                                )
                        )
                        .credential(mongoCredential)
                .build());
    }

    @Bean(name = "myMongoDbFactory")
    @Primary
    public MongoDatabaseFactory mongoDatabaseFactory(
            @Qualifier("myMongoClient") MongoClient mongoClient,
            @Qualifier("myMongoProperties") MongoProperties mongoProperties) {
        return new SimpleMongoClientDatabaseFactory(mongoClient, mongoProperties.getDatabase());
    }

}
