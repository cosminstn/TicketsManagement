package com.stn.tickets.db.dao.services.general;

import com.stn.tickets.db.dao.models.general.Entity;

public interface IEntityDAO<T extends Entity> {

    /**
     * Fetches a persistent entity by it's id.
     * @param id The entity's id
     * @return Returns the entity.
     * @throws Exception Any exception that occurs during database interrogation
     */
    T getEntity(int id) throws Exception;

    /**
     * Persists an entity into the database.
     * @param entity The entity to be persisted
     * @return Returns the entity's primary key value
     * @throws Exception Any exception that occurs during persisting
     */
    int createEntity(T entity) throws Exception;

    /**
     * Update a persistent entity.
     * @param updatedEntity
     * @return Returns the success of the operation
     * @throws Exception Any exception that occurs during update
     */
    boolean updateEntity(T updatedEntity) throws Exception;

    /**
     * Deletes a persistent entity.
     * @param id The entity's id
     * @return Returns the success of the operation
     * @throws Exception Any exception that occurs during deletion
     */
    boolean deleteEntity(int id) throws Exception;

    /**
     * Searches the entity's table and checks if the entity is persistent.
     * @param id The entity's id
     * @return Returns true if there is an entity registered with this id.
     * @throws Exception Any exception that occurs during database interrogation
     */
    boolean isEntityPersistent(int id) throws Exception;
}
