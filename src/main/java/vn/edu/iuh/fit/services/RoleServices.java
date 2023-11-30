package vn.edu.iuh.fit.services;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.entities.Role;
import vn.edu.iuh.fit.repositories.RoleRepository;

public class RoleServices {
    private RoleRepository repository;

    public RoleServices() {
        repository=new RoleRepository();
    }

    public void addRole(Role role){
        repository.addRole(role);
    }
}
