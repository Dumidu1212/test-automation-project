package com.sliit.code.profile;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<ProfileUser, Long> {
}
