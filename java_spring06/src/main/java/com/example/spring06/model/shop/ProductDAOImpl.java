package com.example.spring06.model.shop;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


@Repository
public class ProductDAOImpl implements ProductDAO {
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<ProductDTO> list() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("product.list");
	}

	@Override
	public ProductDTO detail(int product_code) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("product.detail", product_code);
	}

	// auto commit, auto close
	
	@Override
	public void update(ProductDTO dto) {
		// TODO Auto-generated method stub
		sqlSession.update("product.update", dto);
	}

	@Override
	public void delete(int product_code) {
		// TODO Auto-generated method stub
		sqlSession.delete("product.delete", product_code);
	}

	@Override
	public void insert(ProductDTO dto) {
		// TODO Auto-generated method stub
		sqlSession.insert("product.insert", dto);
	}

	@Override
	public String file_info(int product_code) {	// 파일이름 리턴
		// TODO Auto-generated method stub
		return sqlSession.selectOne("product.file_info", product_code);
	}

}
