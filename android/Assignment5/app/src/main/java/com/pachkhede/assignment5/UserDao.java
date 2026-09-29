package com.pachkhede.assignment5;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import java.util.List;

import androidx.room.Query;


@Dao
public interface UserDao {

    @Insert
    void insertUsers(List<User> users);

    @Query("SELECT * FROM User")
    List<User> getAllUsers();

    @Query("UPDATE User SET name = :name, email = :email WHERE id = :id")
    void updateUser(int id, String name, String email);

    @Query("SELECT COUNT(*) FROM User")
    int getCount();

    @Query("DELETE FROM User WHERE id=:id")
    void delete(int id);

}
