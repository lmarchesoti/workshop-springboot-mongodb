package dev.lmarchesoti.workshopmongo.repository;

import dev.lmarchesoti.workshopmongo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findByTitleContainingIgnoreCase(String text);

    @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
    List<Post> searchByTitle(String text);

    @Query("{ $and: [ { 'date': { $gte: ?1 } }, " +
            "         { 'date': { $lt: ?2 } }, " +
            "         { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, " +
            "                  { 'body': { $regex: ?0, $options: 'i' } }, " +
            "                  { 'comments.text': { $regex: ?0, $options: 'i' } } " +
            "                ] " +
            "         } " +
            "       ] " +
            "}")
    List<Post> fullSearch(String text, Instant startDate, Instant finalDate);
}
