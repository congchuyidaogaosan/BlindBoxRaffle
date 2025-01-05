package com.mysterybox.mapper;

import com.mysterybox.entity.BoxSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BoxSeriesRepository extends JpaRepository<BoxSeries, Long> {
    List<BoxSeries> findByStatus(Integer status);
} 