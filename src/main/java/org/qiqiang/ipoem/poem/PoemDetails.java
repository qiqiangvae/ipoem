package org.qiqiang.ipoem.poem;

import lombok.Data;
import org.qiqiang.ipoem.es.ESDocument;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class PoemDetails implements ESDocument {
    @Id
    private String id;
    private String title;
    private String dynasty;
    private String author;
    private String content;
    private String type;
}
