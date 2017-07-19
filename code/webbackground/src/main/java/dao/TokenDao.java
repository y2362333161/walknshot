package dao;

import java.util.List;

import model.Token;

public interface TokenDao {

	public Integer save(Token token);

	public void delete(Token token);

	public void update(Token token);

	public Token getTokenById(int id);

	public List<Token> getAllTokens();

}