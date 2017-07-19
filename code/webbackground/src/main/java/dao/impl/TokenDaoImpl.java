package dao.impl;

import java.util.List;

import model.Token;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.TokenDao;

public class TokenDaoImpl extends HibernateDaoSupport implements TokenDao {

	public Integer save(Token token) {
		return (Integer) getHibernateTemplate().save(token);
	}

	public void delete(Token token) {
		getHibernateTemplate().delete(token);
	}

	public void update(Token token) {
		getHibernateTemplate().merge(token);
	}

	public Token getTokenById(int id) {
		@SuppressWarnings("unchecked")
		List<Token> tokens = (List<Token>) getHibernateTemplate().find(
				"from Token as t where t.id=?", id);
		Token token = tokens.size() > 0 ? tokens.get(0) : null;
		return token;
	}
	
	public List<Token> getAllTokens() {
		@SuppressWarnings("unchecked")
		List<Token> tokens = (List<Token>) getHibernateTemplate()
				.find("from Token");
		return tokens;
	}

}
