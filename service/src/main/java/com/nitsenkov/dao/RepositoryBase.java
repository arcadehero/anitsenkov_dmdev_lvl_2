package com.nitsenkov.dao;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class RepositoryBase<K extends Serializable, E> implements Repository<K, E> {

    private final Class<E> entityClass;
    public final EntityManager entityManager;

    @Override
    public E save(E entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    @Override
    public void delete(E entity) {
        entityManager.remove(entity);
        entityManager.flush();
    }

    @Override
    public void update(E entity) {
        entityManager.merge(entity);
        entityManager.flush();
    }

    @Override
    public Optional<E> findById(K id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }
}
