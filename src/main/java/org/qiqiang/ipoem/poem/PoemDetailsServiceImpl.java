package org.qiqiang.ipoem.poem;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoemDetailsServiceImpl implements IPoemDetailsService {
    private final IPoemDetailsRepository poemDetailsRepository;
    private final MongoTemplate mongoTemplate;

    public PoemDetailsServiceImpl(IPoemDetailsRepository poemDetailsRepository, MongoTemplate mongoTemplate) {
        this.poemDetailsRepository = poemDetailsRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public PoemDetails findById(String id) {
        return poemDetailsRepository.findById(id).orElse(null);
    }

    @Override
    public PoemDetails insertOne(PoemDetails poemDetails) {
        return poemDetailsRepository.insert(poemDetails);
    }

    @Override
    public List<PoemDetails> findByAuthor(String author) {
        return poemDetailsRepository.findByAuthor(author);
    }

    @Override
    public PoemDetails random() {
        AggregationResults<PoemDetails> results = mongoTemplate.aggregate(
                Aggregation.newAggregation(
                        Aggregation.project("title")
                ), PoemDetails.class, PoemDetails.class
        );
        List<PoemDetails> idList = results.getMappedResults();
        int index = RandomUtils.nextInt(0, idList.size());
        return this.findById(idList.get(index).getId());
    }
}
