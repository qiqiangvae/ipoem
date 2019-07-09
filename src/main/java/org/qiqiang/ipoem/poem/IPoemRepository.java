package org.qiqiang.ipoem.poem;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IPoemRepository extends MongoRepository<PoemDetails, String> {

}
