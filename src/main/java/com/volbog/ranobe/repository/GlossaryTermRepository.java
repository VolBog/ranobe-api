package com.volbog.ranobe.repository;

import com.volbog.ranobe.entity.GlossaryTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GlossaryTermRepository extends JpaRepository<GlossaryTerm, Long>,
    JpaSpecificationExecutor<GlossaryTerm> {

}