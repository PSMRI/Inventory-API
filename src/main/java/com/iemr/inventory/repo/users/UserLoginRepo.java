package com.iemr.inventory.repo.users;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iemr.inventory.data.user.M_User;

@Repository
public interface UserLoginRepo extends CrudRepository<M_User, Long> {

	@Query(" SELECT u FROM M_User u WHERE u.userID = :userID AND u.Deleted = false ")
	public M_User getUserByUserID(@Param("userID") Long userID);

	// Resolve the responsible staff member by the username captured in createdBy
	@Query(" SELECT u FROM M_User u WHERE u.UserName = :userName AND u.Deleted = false ")
	public M_User getUserByUserName(@Param("userName") String userName);

}
