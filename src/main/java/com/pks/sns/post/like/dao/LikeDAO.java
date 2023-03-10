package com.pks.sns.post.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {

	public int getLike(
			@Param("userId") int userId
			, @Param("postId") int postId);
	
	
	public int selectLikeCount(@Param("postId") int postId);
	
	
	public int selectLikeCountByUserId(
			@Param("postId") int postId
			, @Param("userId") int userId);

	public int deleteLike(
			@Param("postId") int postId
			, @Param("userId") int userId);
	

	public int deleteLikeByPostId(@Param("postId") int postId);

}
