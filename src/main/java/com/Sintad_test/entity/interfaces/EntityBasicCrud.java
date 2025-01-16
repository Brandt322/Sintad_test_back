package com.Sintad_test.entity.interfaces;

import com.Sintad_test.entity.models.request.EntityDetailsRequest;
import com.Sintad_test.entity.models.response.EntityDetailsResponse;

public interface EntityBasicCrud {

     EntityDetailsResponse findById(Long id);

     EntityDetailsResponse create(EntityDetailsRequest request);

     EntityDetailsResponse update(Long id, EntityDetailsRequest request);

     void delete(Long id);
}
