package com.viaplay.api.utils;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.typeManufacturers.StringTypeManufacturerImpl;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.UUID;

public class CustomStringManufacturerTest extends StringTypeManufacturerImpl {

    private static final String FIELD_ID = "id";
    private static final String FIELD_MBID = "mbid";
    private static final String FIXED_IMAGE_TYPE = "mbid";

    @Override
    public String getType(final DataProviderStrategy strategy, final AttributeMetadata attributeMetadata,
                          final Map<String, Type> genericTypesArgumentsMap) {
        if (FIELD_ID.equals(attributeMetadata.getAttributeName()) || FIELD_MBID.equals(attributeMetadata.getAttributeName())) {
            return UUID.randomUUID().toString();
        }
        return super.getType(strategy, attributeMetadata, genericTypesArgumentsMap);
    };

}