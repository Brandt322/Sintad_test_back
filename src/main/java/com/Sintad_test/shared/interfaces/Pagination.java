package com.Sintad_test.shared.interfaces;

import com.Sintad_test.shared.pagination.PagedResponse;

public interface Pagination <T extends IHandlePaginationRequest, G extends IHandlePaginationResponse> {
    PagedResponse<? extends IHandleCrudResponse> findAll(int page, int size, String search);
}
