package com.Sintad_test.shared.interfaces;

import com.Sintad_test.shared.pagination.PagedResponse;

public interface Pagination < G extends IHandlePaginationResponse> {
    PagedResponse<G> findAll(int page, int size, int documentType, int taxpayerType, String state);
}
