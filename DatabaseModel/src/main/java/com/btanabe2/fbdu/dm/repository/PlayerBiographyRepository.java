package com.btanabe2.fbdu.dm.repository;

import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Brian on 11/27/14.
 */
public interface PlayerBiographyRepository extends CrudRepository<PlayerBiographyEntity, Long> {
    List<PlayerBiographyEntity> findByName(String name);
}
