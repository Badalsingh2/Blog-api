package com.badalcode.blog.blogapi.services.impl;

import com.badalcode.blog.blogapi.entities.Category;
import com.badalcode.blog.blogapi.entities.Post;
import com.badalcode.blog.blogapi.entities.User;
import com.badalcode.blog.blogapi.exceptions.ResourceNotFoundException;
import com.badalcode.blog.blogapi.payload.PostDto;
import com.badalcode.blog.blogapi.payload.PostResponse;
import com.badalcode.blog.blogapi.repositories.PostRepo;
import com.badalcode.blog.blogapi.repositories.UserRepo;
import com.badalcode.blog.blogapi.repositories.categoryRepo;
import com.badalcode.blog.blogapi.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private categoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));
        Category categoryRepo1 = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));

        Post post = this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(categoryRepo1);

        Post newPost = this.postRepo.save(post);
        return this.modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatePost=this.postRepo.save(post);
        return this.modelMapper.map(updatePost,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
        this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {

        Sort sort = null;
        if(sortDir.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        }
        else {
            sort = Sort.by(sortBy).descending();
        }
        Pageable p= PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> pagepost = this.postRepo.findAll(p);
        List<Post> allPosts=pagepost.getContent();
        List<PostDto> postDtos=allPosts.stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();

        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagepost.getNumber());
        postResponse.setPageSize(pagepost.getSize());
        postResponse.setTotalElements(pagepost.getTotalElements());
        postResponse.setTotalPages(pagepost.getTotalPages());
        postResponse.setLastPage(pagepost.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post posst=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post id",postId));

        return this.modelMapper.map(posst,PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {

        Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category id",categoryId));
        List<Post> posts=this.postRepo.findByCategory(cat);
        List<PostDto> postDtos=posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","user id",userId));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyWord) {
        List<Post> posts=this.postRepo.findByTitleContaining(keyWord);
        List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}
