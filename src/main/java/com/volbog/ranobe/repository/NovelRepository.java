package com.volbog.ranobe.repository;

import com.volbog.ranobe.entity.Novel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface NovelRepository extends JpaRepository<Novel, Long>,
    JpaSpecificationExecutor<Novel> {

}