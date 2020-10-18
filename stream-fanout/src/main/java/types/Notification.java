package types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "InvoiceNumber",
    "CustomerCardNo",
    "TotalAmount",
    "EarnedLoyaltyPoints"
})
public class Notification {

    @JsonProperty("InvoiceNumber")
    private String invoiceNumber;
    @JsonProperty("CustomerCardNo")
    private String customerCardNo;
    @JsonProperty("TotalAmount")
    private Double totalAmount;
    @JsonProperty("EarnedLoyaltyPoints")
    private Double earnedLoyaltyPoints;

    @JsonProperty("InvoiceNumber")
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    @JsonProperty("InvoiceNumber")
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Notification withInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        return this;
    }

    @JsonProperty("CustomerCardNo")
    public String getCustomerCardNo() {
        return customerCardNo;
    }

    @JsonProperty("CustomerCardNo")
    public void setCustomerCardNo(String customerCardNo) {
        this.customerCardNo = customerCardNo;
    }

    public Notification withCustomerCardNo(String customerCardNo) {
        this.customerCardNo = customerCardNo;
        return this;
    }

    @JsonProperty("TotalAmount")
    public Double getTotalAmount() {
        return totalAmount;
    }

    @JsonProperty("TotalAmount")
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Notification withTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    @JsonProperty("EarnedLoyaltyPoints")
    public Double getEarnedLoyaltyPoints() {
        return earnedLoyaltyPoints;
    }

    @JsonProperty("EarnedLoyaltyPoints")
    public void setEarnedLoyaltyPoints(Double earnedLoyaltyPoints) {
        this.earnedLoyaltyPoints = earnedLoyaltyPoints;
    }

    public Notification withEarnedLoyaltyPoints(Double earnedLoyaltyPoints) {
        this.earnedLoyaltyPoints = earnedLoyaltyPoints;
        return this;
    }
}

