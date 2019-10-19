package org.project.study.repository;

import org.project.study.model.UserAdditional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAdditionalRepository extends JpaRepository<UserAdditional, Long> {

    UserAdditional findByUserId(Long userId);
}
