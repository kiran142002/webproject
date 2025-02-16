package com.web.kaashivstd;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.*;
import jakarta.transaction.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel,Integer>{
	
	@Query(nativeQuery =true, value="all_student")
	public List<StudentModel>all_student();
	
	@Query(nativeQuery =true, value="one_student :id")
	public StudentModel one_student(@Param("id") int id);
	
	@Query(nativeQuery =true, value=" SELECT * FROM signup_tbl where email=:email and password=:password")
	public StudentModel login_student(@Param("email") String email,@Param("password") String password);
	

	@Transactional
	@Modifying
	@Query(nativeQuery =true, value="sp_insert :email, :password, :name, :course, :fees")
	public void sp_insert(@Param ("email") String email,@Param ("password")String password,@Param ("name")String name,@Param ("course")String course,@Param ("fees")int fees );
	
	@Transactional
	@Modifying
	@Query(nativeQuery =true, value="sp_update :id, :password, :name, :course, :fees")
	public void sp_update(@Param ("id")int id,@Param ("password")String password,@Param ("name")String name,@Param ("course")String course,@Param ("fees")int fees );
	

	@Transactional
	@Modifying
	@Query(nativeQuery =true, value="sp_delete :id")
	public void sp_delete(@Param ("id")int id);


	
	

}
