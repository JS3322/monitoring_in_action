package com.example.management_data.repository;


import com.example.management_data.entity.ScrapEntityExample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapRepository extends JpaRepository<ScrapEntityExample, Long> {
}
