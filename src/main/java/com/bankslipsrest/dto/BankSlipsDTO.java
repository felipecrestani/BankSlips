package com.bankslipsrest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class BankSlipsDTO {

    private String id;
    @JsonProperty("due_date")
    private String dueDate;
    @JsonProperty("total_in_cents")
    private BigDecimal totalInCents;
    private String customer;
    private String status;

}
