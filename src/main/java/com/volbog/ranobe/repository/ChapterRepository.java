package com.volbog.ranobe.repository;

import com.volbog.ranobe.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long>,
    JpaSpecificationExecutor<Chapter> {

}