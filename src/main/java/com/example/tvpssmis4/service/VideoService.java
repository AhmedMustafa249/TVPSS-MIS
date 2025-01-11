package com.example.tvpssmis4.service;

import com.example.tvpssmis4.model.Video;
import com.example.tvpssmis4.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoService {

    @Autowired
    private VideoRepository mVideoRepository;

    public Video addVideo(Video video) {
        return mVideoRepository.save(video);
    }
}
