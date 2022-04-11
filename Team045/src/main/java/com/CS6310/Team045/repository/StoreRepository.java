package com.CS6310.Team045.repository;

import com.CS6310.Team045.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store,String> {
}
