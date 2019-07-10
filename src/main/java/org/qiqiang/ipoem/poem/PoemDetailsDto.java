package org.qiqiang.ipoem.poem;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
public class PoemDetailsDto {
    @Id
    private String id;
    private String title;
    private String dynasty;
    private String author;
    private List<String> lines;
    private String type;
}
