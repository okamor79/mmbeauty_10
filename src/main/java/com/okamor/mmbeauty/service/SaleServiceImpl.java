package com.okamor.mmbeauty.service;

import com.liqpay.LiqPay;
import com.liqpay.LiqPayUtil;
import com.okamor.mmbeauty.model.Client;
import javax.xml.bind.DatatypeConverter;

import com.okamor.mmbeauty.model.Course;
import com.okamor.mmbeauty.model.Sale;
import com.okamor.mmbeauty.repository.ClientRepository;
import com.okamor.mmbeauty.repository.CourseRepository;
import com.okamor.mmbeauty.repository.SaleRepository;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@Service
public class SaleServiceImpl implements SaleService{

    @Autowired
    private final CourseRepository courseRepository;

    @Autowired
    private final SaleRepository saleRepository;

    @Autowired
    private final ClientRepository clientRepository;

    public SaleServiceImpl(CourseRepository courseRepository, SaleRepository saleRepository, ClientRepository clientRepository) {
        this.courseRepository = courseRepository;
        this.saleRepository = saleRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public String newOrder(long clientId, long courseId) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        UUID uuid = UUID.randomUUID();
        Course course = courseRepository.getCourseById(courseId);
        String orderId = uuid.toString().replaceAll("-","");
        String liqpayVer = String.valueOf(StaticValue.LIQPAY_VERSION);
        String amount = String.valueOf(course.getPrice()*course.getDiscount());
        String descript = "Оплата курсу " + course.getUniqCode() + "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("action", "pay");
        params.put("amount", amount);
        params.put("currency", "UAH");
        params.put("description", descript);
        params.put("order_id", orderId);
        params.put("version", liqpayVer);
        params.put("language","ua");

        LiqPay liqpay = new LiqPay(StaticValue.PUBLIC_KEY, StaticValue.PRIVATE_KEY);
        String htmlFormCode = liqpay.cnb_form(params);

        Sale order = new Sale();
        System.out.println(clientRepository.getClientById(clientId));
        order.setClient(clientRepository.getClientById(clientId));
        order.setOrderId(orderId);
        order.setCourse(course);
        order.setDateBuy(new Date());
        saleRepository.save(order);
        return htmlFormCode;
    }

    @Override
    public Sale getAllOrders() {
        return (Sale) saleRepository.findAll();
    }
}
