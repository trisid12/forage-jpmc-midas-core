package com.jpmc.midascore.component;

import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.TransactionRecordRepo;
import com.jpmc.midascore.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@Component
public class KafkaTransactionListener {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRecordRepo transactionRecordRepo;

    @Autowired
    private RestTemplate restTemplate;


    @KafkaListener(topics = "${general.kafka-topic}")
    public void listen(Transaction transaction) {
        Optional<UserRecord> senderOpt = Optional.ofNullable(userRepository.findById(transaction.getSenderId()));
        Optional<UserRecord> recipientOpt = Optional.ofNullable(userRepository.findById(transaction.getRecipientId()));

        if (senderOpt.isPresent() && recipientOpt.isPresent()) {
            UserRecord sender = senderOpt.get();
            UserRecord recipient = recipientOpt.get();

            if (sender.getBalance() >= transaction.getAmount()) {
                // ✅ Step 1: Call Incentive API
                ResponseEntity<Incentive> response = restTemplate.postForEntity(
                        "http://localhost:8080/incentive", transaction, Incentive.class
                );
                float incentiveAmount = response.getBody().getAmount();

                // ✅ Step 2: Update balances
                sender.setBalance(sender.getBalance() - transaction.getAmount());
                recipient.setBalance(recipient.getBalance() + transaction.getAmount() + incentiveAmount);

                // ✅ Step 3: Save updated users
                userRepository.save(sender);
                userRepository.save(recipient);

                // ✅ Step 4: Save transaction + incentive
                TransactionRecord record = new TransactionRecord(transaction.getAmount(), sender, recipient);
                record.setIncentive(incentiveAmount);
                transactionRecordRepo.save(record);
            }
        }
    }
}
