package com.example.rest_api_test.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderRequestDto {
    @NotNull(message = "amount must be filled (NOT NULL)")
    @JsonProperty("amount")
    @Pattern(regexp = "^[0-9]*$", message = "Amount are not allowed using comma")
    @Size(min = 1,max = 12, message = "Amount must be greater than 0 and lower than 999.999.999.999")
    private BigDecimal amount;

    @NotEmpty(message = "invoice_number must be filled")
    @NotNull(message = "invoice_number must be filled (NOT NULL)")
    @JsonProperty(value = "invoice_number")
    @Size(max = 64, message = "Invoice Number cannot be more than 64 Charecter")
    private String invoiceNumber;

    @JsonProperty("currency")
    @Size(max = 3, min = 3, message = "currency must be 3 character based on ISO Currency code")
    @Pattern(regexp="^[A-Z]+$", message = "currency value must be string and use Capital character")
    private String currency;

    @Column(name = "token_id")
    private String tokenId;
}
