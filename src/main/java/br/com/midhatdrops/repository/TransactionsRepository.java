package br.com.midhatdrops.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.midhatdrops.models.Transaction;

public interface TransactionsRepository extends JpaRepository<Transaction, Long> {
  List<Transaction> findByIdEquals(Long id);

  @Query("select t from Transaction t join t.user u where u.id = :id")
  List<Transaction> findAllUserTransactions(@Param("id") Long id, Pageable page);
}
