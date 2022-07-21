package com.andrew.springdatajpa;

import com.andrew.springdatajpa.coupon.entities.Coupon;
import com.andrew.springdatajpa.coupon.repos.CouponRepo;
import com.andrew.springdatajpa.product.entities.Product;
import com.andrew.springdatajpa.product.repos.ProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class MultipledatasourcesApplicationTests {

    @Autowired
    CouponRepo couponRepo;

    @Autowired
    ProductRepo productRepo;

    @Test
    void testSaveCoupon() {
        Coupon coupon = new Coupon();
        coupon.setCode("SUPERSALE");
        coupon.setDiscount(new BigDecimal(20));
        coupon.setExpDate("22-7-2022");
        System.out.println(couponRepo.save(coupon));
    }
    @Test
    void testSaveProduct() {
        Product product = new Product();
        product.setName("MAC BOOK PRO");
        product.setDescription("It's awesome");
        product.setPrice(new BigDecimal(2000));
        product.setCouponCode("SUPERSALE");
        System.out.println(productRepo.save(product));
    }

}
