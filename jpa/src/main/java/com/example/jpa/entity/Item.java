package com.example.jpa.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EntityListeners(value = AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Item {
    // id, item_nm , price , stock_number , item_detail , item_sell_status ,
    // reg_time , update_time
    // 상품아이디, 삼품명 , 가격 , 재고수량 , 상세설명 , 판매상태 , 등록시간, 수정시간
    // 판매상태 : SELL , SOLD_OUT 만 가능
    // 상품아이디는 자동증가

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemid;
    private String itemNm;
    private int price;
    private int stockNumber;

    @Column(length = 200, nullable = false)
    private String itemDetail;
    @Enumerated(EnumType.STRING)
    private ItemStatus itemSellStatus;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime regTime;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime updateTime;

    public enum ItemStatus {
        SELL, SOLD_OUT
    }
}
