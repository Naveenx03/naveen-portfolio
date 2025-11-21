package com.library.dao;

import java.util.List;

import com.library.model.Member;

public interface MemberDAO {

    boolean addMember(Member member);

    boolean updateMember(Member member);

    boolean deleteMember(int id);

    Member getMemberById(int id);

    List<Member> getAllMembers();

    List<Member> searchMembersByName(String name);
} 