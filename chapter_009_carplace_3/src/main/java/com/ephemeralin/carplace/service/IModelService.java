package com.ephemeralin.carplace.service;

import com.ephemeralin.carplace.model.Make;
import com.ephemeralin.carplace.model.Model;

import java.util.List;

/**
 * The interface Car service.
 */
public interface IModelService extends IService<Model> {
    /**
     * Find by make list.
     *
     * @param make the make
     * @return the list
     */
    List<Model> findByMake(Make make);
}
