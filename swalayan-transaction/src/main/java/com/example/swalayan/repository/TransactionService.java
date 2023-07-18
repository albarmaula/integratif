package com.example.swalayan.repository;

import com.example.swalayan.model.Employee;
import com.example.swalayan.model.Product;
import com.example.swalayan.model.Transaction;
import com.example.swalayan.model.TransactionItem;
import com.netflix.discovery.DiscoveryClient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private static final String EMPLOYEE_SERVICE_URL = "http://albarmaula:8090/api/employee/{nip}";
    private static final String PRODUCT_SERVICE_URL = "http://albarmaula:8091/product/{idProduct}";

    private final TransactionRepository transactionRepository;
    private final RestTemplate restTemplate;

    public TransactionService(TransactionRepository transactionRepository, RestTemplate restTemplate) {
        this.transactionRepository = transactionRepository;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public List<Transaction> getAllTransactions() {
        return (List<Transaction>) transactionRepository.findAll();
    }

    @Transactional
    public Transaction createTransaction(Transaction transaction) {

        Employee employee = retrieveEmployee(transaction.getNip());
        if (employee != null) {
            transaction.setNip(employee.getNip());
        }

        for (TransactionItem item : transaction.getTransactionItems()) {
            Product product = retrieveProduct(item.getId_product());
            if (product != null) {
                item.setId_product(product.getId());
                item.setProduct_version(product.getVersion());
                double subTotal = product.getPrice() * item.getQuantity();
                item.setSub_total(subTotal);
            }
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        transaction.setYear(currentDateTime.getYear());
        transaction.setMonth(currentDateTime.getMonthValue());
        transaction.setDay(currentDateTime.getDayOfMonth());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        transaction.setTime(LocalTime.now().format(dtf));

        double totalAmount = transaction.getTransactionItems().stream()
                .mapToDouble(TransactionItem::getSub_total)
                .sum();
        transaction.setTotal_amount(totalAmount);

        return transactionRepository.save(transaction);
    }

    @Transactional
    public Optional<Transaction> findTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    @Transactional
    public List<Transaction> findTransactionsByYear(int year) {
        return transactionRepository.findByYear(year);
    }

    @Transactional
    public List<Transaction> findTransactionByYearAndMonth(int year, int month) {
        return transactionRepository.findByYearAndMonth(year, month);
    }

    @Transactional
    public List<Transaction> findTransactionByYearAndMonthAndDay(int year, int month, int day) {
        return transactionRepository.findByYearAndMonthAndDay(year, month, day);
    }

    @Transactional
    public List<Transaction> findTransactionsByNip(Long nip) {
        return transactionRepository.findByNip(nip);
    }

    @Transactional
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    private Employee retrieveEmployee(Long nip) {
        return restTemplate.getForObject(EMPLOYEE_SERVICE_URL, Employee.class, nip);
    }

    private Product retrieveProduct(Long idProduct) {
        return restTemplate.getForObject(PRODUCT_SERVICE_URL, Product.class, idProduct);
    }
}