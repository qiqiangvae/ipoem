package org.qiqiang.ipoem.poem;

import java.util.List;

public interface IPoemDetailsService {
    PoemDetails findById(String id);

    PoemDetails insertOne(PoemDetails poemDetails);

    List<PoemDetails> findByAuthor(String author);

    PoemDetails random();
}
