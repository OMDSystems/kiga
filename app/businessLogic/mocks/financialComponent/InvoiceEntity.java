/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic.mocks.financialComponent;

import businessLogic.customerComponent.IChildData;
import java.util.List;

/**
 *
 * @author Marvin
 */
public class InvoiceEntity implements IInvoiceData{
  
  private Long invoiceNumber;

  /**
   * Get the value of invoiceNumber
   *
   * @return the value of invoiceNumber
   */
  public Long getInvoiceNumber() {
    return invoiceNumber;
  }

  /**
   * Set the value of invoiceNumber
   *
   * @param invoiceNumber new value of invoiceNumber
   */
  public void setInvoiceNumber(Long invoiceNumber) {
    this.invoiceNumber = invoiceNumber;
  }

  protected String name;

  /**
   * Get the value of name
   *
   * @return the value of name
   */
  public String getName() {
    return name;
  }

  /**
   * Set the value of name
   *
   * @param name new value of name
   */
  public void setName(String name) {
    this.name = name;
  }

  protected String lastName;

  /**
   * Get the value of lastName
   *
   * @return the value of lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Set the value of lastName
   *
   * @param lastName new value of lastName
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  protected Double amount;

  /**
   * Get the value of amount
   *
   * @return the value of amount
   */
  public Double getAmount() {
    return amount;
  }

  /**
   * Set the value of amount
   *
   * @param amount new value of amount
   */
  public void setAmount(Double amount) {
    this.amount = amount;
  }

  
  public InvoiceEntity(Long invoiceNumber, String name, String lastName, Double amount) {
    this.invoiceNumber = invoiceNumber;
    this.name = name;
    this.lastName = lastName;
    this.amount = amount;
  }

  public Double getAmountInEuro() {
    return getAmount();
  }
  
  
  
}
