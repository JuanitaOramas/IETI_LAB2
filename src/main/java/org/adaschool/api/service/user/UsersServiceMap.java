package org.adaschool.api.service.user;

import org.adaschool.api.repository.user.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceMap implements UsersService {

    HashMap<Integer,User> storeUser = new HashMap<>();
    static Integer id = 1;

    @Override
    public User save(User user) {
        storeUser.put(id,user);
        id++;
        return user;
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<User> all() {
        return (List<User>) storeUser;
    }

    @Override
    public void deleteById(String id) {
        storeUser.remove(Integer.valueOf(id));
    }

    @Override
    public User update(User user, String userId) {
        storeUser.put(Integer.valueOf(id),user);
        return user;
    }
}
