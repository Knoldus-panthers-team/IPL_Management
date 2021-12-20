package com.knoldus.kup.ipl.IPL_Management_System.repository;

import com.knoldus.kup.ipl.IPL_Management_System.models.PointTable;
import org.springframework.data.repository.CrudRepository;

public interface PointRepository extends CrudRepository<PointTable,Long> {
    public PointTable findByTeamId(Long id);
}
