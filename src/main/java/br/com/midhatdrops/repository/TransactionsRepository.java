package br.com.midhatdrops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.midhatdrops.models.Transaction;

public interface TransactionsRepository extends JpaRepository<Transaction, Long> {

}
