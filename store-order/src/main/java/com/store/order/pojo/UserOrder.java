package com.store.order.pojo;

import java.util.Date;

public class UserOrder {
	 private String orderId;
	 private String payment;
	 private Integer paymentType;
	 private Integer status;
	 private Date createTime;
	 private String postFee;
	 private Long userId;
	 private String buyerMessage;
	 private String buyerNick;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public Integer getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getPostFee() {
		return postFee;
	}
	public void setPostFee(String postFee) {
		this.postFee = postFee;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getBuyerMessage() {
		return buyerMessage;
	}
	public void setBuyerMessage(String buyerMessage) {
		this.buyerMessage = buyerMessage;
	}
	public String getBuyerNick() {
		return buyerNick;
	}
	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}
	public UserOrder(String orderId, String payment, Integer paymentType, Integer status, Date createTime,
			String postFee, Long userId, String buyerMessage, String buyerNick) {
		super();
		this.orderId = orderId;
		this.payment = payment;
		this.paymentType = paymentType;
		this.status = status;
		this.createTime = createTime;
		this.postFee = postFee;
		this.userId = userId;
		this.buyerMessage = buyerMessage;
		this.buyerNick = buyerNick;
	}
	public UserOrder() {
		super();
	}
	
}
