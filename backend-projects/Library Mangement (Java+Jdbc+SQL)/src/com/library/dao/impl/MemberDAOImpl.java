package com.library.dao.impl;

import java.util.List;

import java.util.ArrayList;

import java.sql.*;

import com.library.dao.MemberDAO;

import com.library.model.Member;

import com.library.util.DBConnection;

public class MemberDAOImpl implements MemberDAO {

    @Override
    public boolean addMember(Member member) {
        String query = "INSERT INTO members(name,email,phone) VALUES(?,?,?)";
        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, member.getName());
            preparedStatement.setString(2, member.getEmail());
            preparedStatement.setString(3, member.getPhone());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows>0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteMember(int id) {
        String query = "DELETE FROM member WHERE member_id = ?";
        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Member> getAllMembers() {
        List<Member> allMembers = new ArrayList<>();
        String query = "SELECT * FROM members";
        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()==false) return new ArrayList<>();
            while(resultSet.next()){
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                allMembers.add(new Member(name, email, phone));
            }
            return allMembers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Member> searchMembersByName(String name) {
        List<Member> allMembers = new ArrayList<>();
        String query = "SELECT * FROM members WHERE name LIKE ?";
        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()==false) return new ArrayList<>();
            while(resultSet.next()){
                String memberName = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                allMembers.add(new Member(memberName, email, phone));
            }
            return allMembers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateMember(Member member) {
        String query = "UPDATE members SET name = ?, email = ?, phone = ? WHERE member_id = ?";
        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, member.getName());
            preparedStatement.setString(2, member.getEmail());
            preparedStatement.setString(3, member.getPhone());
            preparedStatement.setInt(4, member.getMemberId());
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public Member getMemberById(int id){
        String query = "SELECT * FROM members WHERE member_id = ?";
        try(PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()) return null;
            return new Member(resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("phone"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
