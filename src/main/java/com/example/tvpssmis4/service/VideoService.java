package com.example.tvpssmis4.service;

import com.example.tvpssmis4.model.SchoolInformation;
import com.example.tvpssmis4.model.Video;
import com.example.tvpssmis4.repository.VideoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VideoService {

    @Autowired
    private VideoRepository mVideoRepository;

    public Video addVideo(Video video) {
        return mVideoRepository.save(video);
    }

    public Video getVideoById(Long id) {
        return mVideoRepository.findById(id).orElse(null);
    }

    public void deleteVideo(Video video) {
        mVideoRepository.delete(video);
    }

    @Transactional
    public Video updateVideo(Video video) {
//        System.out.println("The crrent: id: " + video.getId());
        Video existingVideo = mVideoRepository.findById(video.getId())
                .orElseThrow(() -> new RuntimeException("Video not found"));

        existingVideo.setTitle(video.getTitle());
        existingVideo.setDescription(video.getDescription());
        existingVideo.setVideoUrl(video.getVideoUrl());
        existingVideo.setThumbnailUrl(video.getThumbnailUrl());
        existingVideo.setUploadDate(LocalDateTime.now());
        existingVideo.setVisibility(video.getvisibility());


        return mVideoRepository.save(existingVideo);
    }

    public List<Video> getAllVideos() {
        return mVideoRepository.findAll();
    }
}
