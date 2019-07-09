package org.qiqiang.ipoem.poem;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IPoemDetailsRepository extends MongoRepository<PoemDetails, String> {

}
