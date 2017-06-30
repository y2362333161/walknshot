package dao;

import java.util.List;

import model.Comment;

public interface CommentDao {

	public Integer save(Comment comment);

	public void delete(Comment comment);

	public void update(Comment comment);

	public Comment getCommentById(int id);

	public List<Comment> getAllComments();

}