package org.qiqiang.ipoem.poem;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PoemDetailsController {
    private final IPoemDetailsService poemDetailsService;

    public PoemDetailsController(IPoemDetailsService poemDetailsService) {
        this.poemDetailsService = poemDetailsService;
    }

    @GetMapping("/poemDetails/{id}")
    public PoemDetails findById(@PathVariable String id) {
        return poemDetailsService.findById(id);
    }

    @GetMapping("/poemDetails")
    public PoemDetailsDto random(@RequestParam Boolean random) {
        PoemDetails poem = poemDetailsService.random();
        PoemDetailsDto dto = new PoemDetailsDto();
        BeanUtils.copyProperties(poem, dto);
        dto.setLines(PoemContentConvertor.content2Lines(poem.getContent()));
        return dto;

    }
}
