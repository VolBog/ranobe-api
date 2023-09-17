package com.volbog.ranobe.repository;

import com.volbog.ranobe.entity.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationRepository extends JpaRepository<Translation, Long>,
    JpaSpecificationExecutor<Translation> {

}