package br.com.midhatdrops.utils;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.midhatdrops.models.Transaction;

public interface FormConvert {
  abstract Transaction convert(JpaRepository repository, Long id);
}
