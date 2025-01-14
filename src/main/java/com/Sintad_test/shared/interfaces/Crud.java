package com.Sintad_test.shared.interfaces;

import java.util.List;

public interface Crud<T extends IHandleCrudRequest, G extends IHandleCrudResponse> {
    List<G> findAll();
    G findById(Long id);
    G create(T request);
    G update(Long id, T request);
    void delete(Long id);
}
