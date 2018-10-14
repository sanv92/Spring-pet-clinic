package com.example.demo.services.map;

import com.example.demo.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long>{
    protected Map<Long, T> map = new HashMap<>();

    protected Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    protected T findById(ID id) {
        return map.get(id);
    }

    protected T save(T object) {
        object.setId(getNextId());
        map.put(object.getId(), object);

        return object;
    }

    protected void deleteById(ID id) {
        map.remove(id);
    }

    protected void delete(T object) {
        map.entrySet().removeIf(idtEntry -> idtEntry.getValue().equals(object));
    }

    protected Long getNextId() {
        int size = map.keySet().size();

        return size > 0 ? size + 1 : 1L;
    }
}
