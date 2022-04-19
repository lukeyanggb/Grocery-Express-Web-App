package com.CS6310.Team045.repository;

import com.CS6310.Team045.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
    List<Item> getItemByStore(String store);
    //Optional<Item> getItemByStoreAndName(String store, String name);

}
