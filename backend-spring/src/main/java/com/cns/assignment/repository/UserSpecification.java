package com.cns.assignment.repository;

import com.cns.assignment.model.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<User> notTheOwner(Long id){
        return (user, criteriaQuery, cirteriaBuilder) ->
                cirteriaBuilder.notEqual(user.get("id"), id);
    }
}
