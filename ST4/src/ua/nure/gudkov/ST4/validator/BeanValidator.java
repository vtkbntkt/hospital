package ua.nure.gudkov.ST4.validator;

import ua.nure.gudkov.ST4.entity.ErrorHolder;

/**
 * Bean validator interface.
 * 
 * @author A.Gudkov
 *
 */
public interface BeanValidator<E> {
    /**
     * Validate user form bean.
     *
     * @param e the form bean
     * @return the error holder
     */
    ErrorHolder validate(E e);
}
