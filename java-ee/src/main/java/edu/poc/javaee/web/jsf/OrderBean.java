package edu.poc.javaee.web.jsf;

import javax.faces.flow.FlowScoped;
import javax.inject.Named;

@Named
@FlowScoped("order")
public class OrderBean {

  private int itemCount;
  private String address;

  public int getItemCount() {
    return itemCount;
  }

  public void setItemCount(int itemCount) {
    this.itemCount = itemCount;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getReturnValue() {
    return "/home";
  }

}