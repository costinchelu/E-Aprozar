package ro.ase.service.impl;

import org.springframework.stereotype.Service;

import ro.ase.entity.AuthorityType;
import ro.ase.entity.InfoUser;
import ro.ase.jdbc.InfoUserDAO;
import ro.ase.model.UserRegistration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ro.ase.service.UserServiceInterface;

@Service
public class UserServiceImp implements UserServiceInterface {

    @Override
    public boolean userAlreadyExist(String email) {
        InfoUserDAO dao = new InfoUserDAO();
        InfoUser infoUser = dao.findByEmail(email);
        return infoUser != null;
    }

    @Override
    public void save(UserRegistration user) {
        InfoUserDAO dao = new InfoUserDAO();
        InfoUser infoUser = new InfoUser();
        infoUser.setName(user.getName());
        infoUser.setEmail(user.getEmail());
        infoUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        infoUser.setEnabled(true);
        infoUser.setAuthority(AuthorityType.USER);
        dao.save(infoUser);
    }

    @Override
    public InfoUser findByEmail(String email) {
        InfoUserDAO dao = new InfoUserDAO();
        return dao.findByEmail(email);
    }

    @Override
    public void update(InfoUser user) {
        InfoUserDAO dao = new InfoUserDAO();
        dao.update(user);
    }
}
