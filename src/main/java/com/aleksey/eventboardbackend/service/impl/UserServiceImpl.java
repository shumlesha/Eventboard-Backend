package com.aleksey.eventboardbackend.service.impl;

import com.aleksey.eventboardbackend.dto.user.ManagerDto;
import com.aleksey.eventboardbackend.dto.user.UserDto;
import com.aleksey.eventboardbackend.entity.user.Manager;
import com.aleksey.eventboardbackend.entity.user.Student;
import com.aleksey.eventboardbackend.entity.user.User;
import com.aleksey.eventboardbackend.exception.user.CantConfirmManagerException;
import com.aleksey.eventboardbackend.exception.user.CantConfirmSelfManagerException;
import com.aleksey.eventboardbackend.exception.user.ManagerAlreadyConfirmedException;
import com.aleksey.eventboardbackend.exception.user.UserNotFoundException;
import com.aleksey.eventboardbackend.mapper.UserMapper;
import com.aleksey.eventboardbackend.repository.user.ManagerRepository;
import com.aleksey.eventboardbackend.repository.user.StudentRepository;
import com.aleksey.eventboardbackend.repository.user.UserRepository;
import com.aleksey.eventboardbackend.security.CurrentUser;
import com.aleksey.eventboardbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ManagerRepository managerRepository;
    private final StudentRepository studentRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    @Transactional(readOnly = true)
    public Manager getManagerByEmail(String email) {
        return managerRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    @Transactional(readOnly = true)
    public Manager getManagerByEmailOrNull(String email) {
        return managerRepository.findByEmail(email)
                .orElse(null);
    }


    @Override
    @Transactional(readOnly = true)
    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    @Transactional
    public void confirmManager(UUID id, CurrentUser currentUser) {
        Manager manager = managerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        if (manager.isConfirmed()) {
            throw new ManagerAlreadyConfirmedException();
        }

        if (currentUser.isManager()) {
            Manager mainManager = managerRepository.findByEmail(currentUser.getEmail())
                    .orElseThrow(() -> new UserNotFoundException(currentUser.getEmail()));

            if (mainManager.getEmail().equalsIgnoreCase(manager.getEmail())) {
                throw new CantConfirmSelfManagerException();
            }

            if (!manager.isInSameCompany(mainManager.getCompany())) {
                throw new CantConfirmManagerException();
            }
        }

        manager.confirm();

        managerRepository.save(manager);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ManagerDto> getAllManagers(CurrentUser currentUser, Pageable pageable) {
        if (currentUser.isManager()) {
            Manager mainManager = managerRepository.findByEmail(currentUser.getEmail())
                    .orElseThrow(() -> new UserNotFoundException(currentUser.getEmail()));

            return managerRepository.findAllByCompany(mainManager.getCompany(), pageable).map(userMapper::toManagerDto);
        }
        else {
            return managerRepository.findAll(pageable).map(userMapper::toManagerDto);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ManagerDto getMyManagerProfile(CurrentUser currentUser) {
        Manager manager =  managerRepository.findByEmail(currentUser.getEmail())
                .orElseThrow(() -> new UserNotFoundException(currentUser.getEmail()));

        return userMapper.toManagerDto(manager);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getMyAccount(CurrentUser currentUser) {
        User user = userRepository.findByEmail(currentUser.getEmail())
                .orElseThrow(() -> new UserNotFoundException(currentUser.getEmail()));

        return userMapper.toDto(user);
    }
}
