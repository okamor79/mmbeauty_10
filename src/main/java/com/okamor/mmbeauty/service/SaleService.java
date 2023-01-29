package com.okamor.mmbeauty.service;

import com.okamor.mmbeauty.model.Sale;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface SaleService {

    String newOrder(long clientID, long courseId) throws NoSuchAlgorithmException, UnsupportedEncodingException;

    Sale getAllOrders();
}
