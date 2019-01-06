package com.project.myProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.myProject.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer > {

}
