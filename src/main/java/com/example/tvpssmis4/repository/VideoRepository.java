package com.example.tvpssmis4.repository;

import com.example.tvpssmis4.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository <Video, Long>{


}
