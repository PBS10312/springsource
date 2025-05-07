package com.example.jpa.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import com.example.jpa.entity.Item;
import com.example.jpa.entity.QItem;
import com.example.jpa.entity.Item.ItemStatus;
import com.querydsl.core.BooleanBuilder;

@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void queryDslTest() {
        // where itemNm = 'item2'
        QItem item = QItem.item;
        // System.out.println(itemRepository.findAll(item.itemNm.eq("item2")));

        // where itemNm like 'item2%'
        // System.out.println(itemRepository.findAll(item.itemNm.startsWith("i")));

        // where itemNm like '%item2'
        // System.out.println(itemRepository.findAll(item.itemNm.endsWith("item2")));

        // where itemNm like '%item2%'
        // System.out.println(itemRepository.findAll(item.itemNm.contains("item2")));

        // where itemNm = 'item2' and price > 1000
        // itemRepository.findAll(item.itemNm.eq("item2")
        // .and(item.price.gt(1000)));
        // System.out.println(item);

        // where itemNm = 'item2' and price >= 1000
        itemRepository.findAll(item.itemNm.eq("item2")
                .and(item.price.goe(1000)));
        System.out.println(item);
        // where itemNm like'%item2%' or itemSellStatus = SOLD_OUT
        System.out.println(
                itemRepository.findAll(item.itemNm.contains("item2").or(item.itemSellStatus.eq(ItemStatus.SOLD_OUT))));
        // where stockNumber >= 30
        System.out.println(itemRepository.findAll(item.stockNumber.goe(30)));
        // where price < 35000
        System.out.println(itemRepository.findAll(item.price.lt(35000)));

        // 조건 : booleanBuilder
        BooleanBuilder builder = new BooleanBuilder();
        // where itemNm = 'item2' and price > 1000
        builder.and(item.itemNm.eq("item2"));
        builder.and(item.price.gt(1000));
        System.out.println(itemRepository.findAll(builder));
    }

    @Test
    public void insertTest() {

        IntStream.rangeClosed(1, 50).forEach(i -> {
            Item item = Item.builder()
                    .itemNm("item" + i)
                    .price(i * 2000)
                    .stockNumber(i + 10)
                    .itemDetail("item Detail" + i)
                    .itemSellStatus(ItemStatus.SELL)
                    .build();

            itemRepository.save(item);
        });
    }

    @Test
    public void aggreateTest() {
        List<Object[]> result = itemRepository.aggreate();

        for (Object[] object : result) {
            System.out.println(Arrays.toString(object)); // [50, 2550000, 51000.0, 100000, 2000]
            System.out.println("아이템 수 : " + object[0]);
            System.out.println("아이템 가격 합 : " + object[1]);
            System.out.println("아이템 가격 평균 : " + object[2]);
            System.out.println("아이템 가격 최대값 : " + object[3]);
            System.out.println("아이템 가격 최소값 : " + object[4]);
        }
    }
}
