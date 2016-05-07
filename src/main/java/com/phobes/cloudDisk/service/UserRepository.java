package com.phobes.cloudDisk.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.phobes.cloudDisk.domain.User;
@Transactional
public interface UserRepository extends CrudRepository<User, Long>{
	public User findByUsername(String username);
}
