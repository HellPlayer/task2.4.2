package task2_3_1.dao;

import task2_3_1.model.User;

import java.util.List;

public interface UserDao {

    void add(User user);

    void delete(Long id);

    void edit(User user);

    User getById(Long userId);

    List<User> getAll();
}
