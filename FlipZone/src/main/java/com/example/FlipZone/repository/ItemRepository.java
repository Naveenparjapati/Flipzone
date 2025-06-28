package com.example.FlipZone.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FlipZone.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
