package com.pks.sns.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pks.sns.post.bo.PostBO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/post")
public class PostRestController {

	@Autowired
	private PostBO postBO;
	
	
	@PostMapping("/write")
	public Map<String, String> writePost(
			@RequestParam("content") String content
			, @RequestParam("file") MultipartFile file
// 아래처럼 표현 가능			, HttpServletRequest request) {
			, HttpSession session){
//	이두개 합한거	HttpSession session = request.getSession();
		
		int userId = (Integer)session.getAttribute("userId");
		
		int count = postBO.addPost(userId ,content, file);
		Map<String, String> result = new HashMap<>();
		
		if(count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
		
	}
	
	@GetMapping("/like")
	public Map<String, String> like(
			@RequestParam("postId") int postId
			, HttpSession session){
		
		int userId =  (Integer)session.getAttribute("userId");
		
		int count = postBO.addLike(userId, postId);
		
		
		Map<String, String> result = new HashMap<>();
		
		if(count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}
	
}
