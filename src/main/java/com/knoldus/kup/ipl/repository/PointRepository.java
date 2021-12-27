package com.knoldus.kup.ipl.repository;

import com.knoldus.kup.ipl.models.PointTable;
import org.springframework.data.repository.CrudRepository;

public interface PointRepository extends CrudRepository<PointTable,Long> {
    PointTable findByTeamId(Long id);
}
