package com.cg.loanapp.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransacRepository extends JpaRepository<TransacEntity, Integer>{

	@Query("select t from TransacEntity t where t.customerEntity.accnum=?1")
	public List<TransacEntity> getTransactionList(int accnum);

}
