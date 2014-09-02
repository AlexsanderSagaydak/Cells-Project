package com.xb.safe.util;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import java.util.Collections;
import java.util.Set;

public class DozerConverter implements GenericConverter, InitializingBean {
    private Mapper mapper;

    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(Object.class, Object.class));
    }

    public Object convert(Object aSource, TypeDescriptor aSourceType, TypeDescriptor aTargetType) {
        return mapper.map(aSource, aTargetType.getType());
    }

    public void afterPropertiesSet() {
        if (mapper == null) {
            mapper = new DozerBeanMapper();
        }
    }

    public void setMapper(final Mapper aMapper) {
        mapper = aMapper;
    }

    public Mapper getMapper() {
        return mapper;
    }
}
