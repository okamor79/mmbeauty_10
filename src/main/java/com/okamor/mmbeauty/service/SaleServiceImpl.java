package com.okamor.mmbeauty.service;

import com.liqpay.LiqPay;
import com.okamor.mmbeauty.model.Course;
import com.okamor.mmbeauty.model.Sale;
import com.okamor.mmbeauty.repository.CourseRepository;
import com.okamor.mmbeauty.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class SaleServiceImpl implements SaleService{

    private static final String PUBLIC_KEY = "sandbox_i95838346916";
    private static final String PRIVATE_KEY = "sandbox_LNqBdEb7fiJILtTy1hNxFXidEY2asDbUMeHke60O";
    @Autowired
    private final CourseRepository courseRepository;

    @Autowired
    private final SaleRepository saleRepository;

    public SaleServiceImpl(CourseRepository courseRepository, SaleRepository saleRepository) {
        this.courseRepository = courseRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public String newOrder(long id) {
        UUID uuid = UUID.randomUUID();
        String orderId = uuid.toString();
        Course course = courseRepository.getCourseById(id);
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("action", "pay");
        params.put("amount", String.valueOf(course.getPrice()*course.getDiscount()));
        params.put("currency", "UAH");
        params.put("description", "Оплата курсу  " + course.getUniqCode());
        params.put("order_id", orderId);
        params.put("version", "3");
        LiqPay liqpay = new LiqPay(PUBLIC_KEY, PRIVATE_KEY);
        String html = liqpay.cnb_form(params);


        return html;
    }
}
