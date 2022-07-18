package com.andrew.springdatajpa.coupon.repos;

import com.andrew.springdatajpa.coupon.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepo extends JpaRepository<Coupon, Long> {



}
