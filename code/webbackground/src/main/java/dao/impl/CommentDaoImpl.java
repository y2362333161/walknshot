package dao.impl;

import java.util.List;

import model.Comment;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.CommentDao;

public class CommentDaoImpl extends HibernateDaoSupport implements CommentDao {

	public Integer save(Comment comment) {
		return (Integer) getHibernateTemplate().save(comment);
	}

	public void delete(Comment comment) {
		getHibernateTemplate().delete(comment);
	}

	public void update(Comment comment) {
		getHibernateTemplate().merge(comment);
	}

	public Comment getCommentById(int id) {
		@SuppressWarnings("unchecked")
		List<Comment> comments = (List<Comment>) getHibernateTemplate().find(
				"from Comment as c where c.id=?", id);
		Comment comment = comments.size() > 0 ? comments.get(0) : null;
		return comment;
	}

	public List<Comment> getAllComments() {
		@SuppressWarnings("unchecked")
		List<Comment> comments = (List<Comment>) getHibernateTemplate()
				.find("from Comment");
		return comments;
	}

}
