package com.java4all.copy;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;

import java.lang.ref.WeakReference;

import static org.dozer.loader.api.TypeMappingOptions.mapEmptyString;
import static org.dozer.loader.api.TypeMappingOptions.mapNull;

/**
 * Created by YS on 2017/7/27.
 */
public class DozerUtil {

    public static <T> T copy(Object object, Class<T> c) {
        WeakReference weakReference = new WeakReference(new DozerBeanMapper());
        Mapper mapper = new DozerBeanMapper();
        try {
            return mapper.map(object, c);
        } catch (Exception e) {
            return null;
        }
    }

    public static void copyProperties(final Object sources, final Object destination) {
        WeakReference weakReference = new WeakReference(new DozerBeanMapper());
        DozerBeanMapper mapper = (DozerBeanMapper) weakReference.get();
        mapper.addMapping(new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(sources.getClass(), destination.getClass(), mapNull(false), mapEmptyString(false));
            }
        });
        mapper.map(sources, destination);
        mapper.destroy();
        weakReference.clear();
    }

    public static void copyAll(final Object sources, final Object destination){
        WeakReference weakReference = new WeakReference(new DozerBeanMapper());
        DozerBeanMapper mapper = (DozerBeanMapper) weakReference.get();
        mapper.map(sources, destination);
        mapper.destroy();
        weakReference.clear();
    }
}
