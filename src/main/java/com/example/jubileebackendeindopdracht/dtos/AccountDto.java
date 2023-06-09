package com.example.jubileebackendeindopdracht.dtos;

import com.example.jubileebackendeindopdracht.models.SavingGoal;
import com.example.jubileebackendeindopdracht.models.Transaction;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

public class AccountDto {

    private Long id;
    private String username;
    private UserDto user;
    private String email;
    private LocalDate dateCreated;
    private BigDecimal balance;
    private List<Transaction> transactionList;
    private List<SavingGoal> savingGoalList;

    @Valid
    private Transaction transaction;
    private Long transactionId;
    private SavingGoal savingGoal;
    private Long savingGoalId;


}
