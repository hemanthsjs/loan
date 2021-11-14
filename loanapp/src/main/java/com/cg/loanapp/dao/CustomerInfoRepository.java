package com.cg.loanapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;

public interface CustomerInfoRepository extends JpaRepository<CustomerInfoEntity, Integer>{

}
