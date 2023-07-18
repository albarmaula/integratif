package com.example.swalayan.repository;

import com.example.swalayan.model.Employee;
import com.example.swalayan.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends CrudRepository <Transaction, Long> {
    
//    List<Transaction> findByDay(int day);
//
//    List<Transaction> findByMonth(int month);

    List<Transaction> findByYear(int year);

    List<Transaction> findByYearAndMonth(int year, int month);

    List<Transaction> findByYearAndMonthAndDay(int year, int month, int day);

    List<Transaction> findByNip(Long nip);

}
