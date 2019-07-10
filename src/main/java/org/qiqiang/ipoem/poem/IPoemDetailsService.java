package org.qiqiang.ipoem.poem;

import java.util.List;

public interface IPoemDetailsService {
    PoemDetails findById(String id);

    String insertOne(PoemDetails poemDetails);

    List<PoemDetails> findByAuthor(String author);

    PoemDetails random();
}
