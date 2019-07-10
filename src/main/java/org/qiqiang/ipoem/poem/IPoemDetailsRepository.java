package org.qiqiang.ipoem.poem;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IPoemDetailsRepository extends MongoRepository<PoemDetails, String> {
    List<PoemDetails> findByAuthor(String author);

}
