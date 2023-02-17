package com.pks.sns.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pks.sns.common.FileManagerService;
import com.pks.sns.post.dao.PostDAO;
import com.pks.sns.post.model.Post;
import com.pks.sns.post.model.PostDetail;
import com.pks.sns.user.bo.UserBO;
import com.pks.sns.user.model.User;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	// 오토와이어디 어노테이션은 각각 해줘야함 1개로 안됨
	@Autowired
	private UserBO userBO;
	
	public int addPost(int userId, String content, MultipartFile file) {
		// 파일을 저장하고, 접근 경로를 만든다.
		
		String imagePath = FileManagerService.saveFile(userId, file);
		
		return postDAO.insertPost(userId,content, imagePath);
	}
	
	public List<PostDetail> getPostList() {
		
		List<Post> postList = postDAO.selectPost();
		
		// 생성된 PostDetail 객체를 리스트로 구성한다.
		List<PostDetail> postDetailList = new ArrayList<>();
		
		for(Post post:postList) {
			// postDetail 객체를 생성하고, post 객체의 정보를 저장한다.
			PostDetail postDetail = new PostDetail();
			postDetail.setId(post.getId());
			postDetail.setUserId(post.getUserId());
			postDetail.setContent(post.getContent());
			postDetail.setImagePath(post.getImagePath());
			
			User user = userBO.getUserById(post.getUserId());
			
			// UserName 값을 저장한다.
			postDetail.setUserName(user.getName());
			
			postDetailList.add(postDetail);
		}
		
		return postDetailList;
	
	}
}
