package com.pks.sns.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.pks.sns.post.model.Post;
import com.pks.sns.post.model.PostDetail;

@Repository
public interface PostDAO {

	public int insertPost(
			@Param("userId") int userId
			, @Param("content") String content
			, @Param("imagePath") String imagePath);
	
	public List<Post> selectPost();
	
	
	public Post	selectPostByPostId(@Param("postId") int postId);
	
	public int deletePost(
			@Param("postId") int postId
			, @Param("userId") int userId);

}
