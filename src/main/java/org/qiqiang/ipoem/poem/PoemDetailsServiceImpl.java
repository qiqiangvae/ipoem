package org.qiqiang.ipoem.poem;

import org.springframework.stereotype.Service;

@Service
public class PoemDetailsServiceImpl implements IPoemDetailsService {
    private final IPoemDetailsRepository poemDetailsRepository;

    public PoemDetailsServiceImpl(IPoemDetailsRepository poemDetailsRepository) {
        this.poemDetailsRepository = poemDetailsRepository;
    }

    @Override
    public PoemDetails getOne(String id) {
        return poemDetailsRepository.findById(id).orElse(null);
    }
}
