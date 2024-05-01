package com.sian.javaprojecttemplates.restvue.model.repository;

import com.sian.javaprojecttemplates.restvue.common.SessionFactory;
import com.sian.javaprojecttemplates.restvue.model.entity.Person;
import org.apache.ibatis.session.SqlSession;
import java.util.List;

public class PersonDA {
    public List<Person> findAll() throws Exception {
        try (SqlSession session = SessionFactory.getSqlSession()) {
            return session.selectList("PersonMapper.findAll");
        }
    }

    public Person findById(Long id) throws Exception {
        try (SqlSession session =  SessionFactory.getSqlSession()) {
            return session.selectOne("PersonMapper.findById", id);
        }
    }

    public void insert(Person person) throws Exception {
        try (SqlSession session =  SessionFactory.getSqlSession()) {
            session.insert("PersonMapper.insert", person);
            session.commit();
        }
    }

    public void update(Person person) throws Exception {
        try (SqlSession session =  SessionFactory.getSqlSession()) {
            session.update("PersonMapper.update", person);
            session.commit();
        }
    }

    public void delete(long id) throws Exception {
        try (SqlSession session =  SessionFactory.getSqlSession()) {
            session.delete("PersonMapper.delete", id);
            session.commit();
        }
    }
}
