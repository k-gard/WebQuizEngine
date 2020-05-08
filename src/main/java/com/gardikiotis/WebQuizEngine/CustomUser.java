package com.gardikiotis.WebQuizEngine;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
public class CustomUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    @Email(regexp = ".+@.+\\..+")
    private String email;
    @Column
    @Length(min = 5)
    private String password;

    private String roles = "";

    private String permissions = "";


    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "UserId", nullable = false)
    List<Quiz> quizzes = new ArrayList<>();

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "UserId", nullable = false)
    List<CompletedQuiz> completedQuizzes = new ArrayList<>();


    public CustomUser(String email, @Length(min = 5) String password) {
        this.email = email;
        this.password = password;
    }

    public CustomUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public void setPassword(String password) {
        this.password = password;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public void addQuiz(Quiz quiz) {
        this.quizzes.add(quiz);
    }

    public boolean createdQuizWithId(int id) {
  /*  for (Quiz q : quizzes){
        if (q.getId() == id){
            return true;
        }
    }
    return false;
    }*/
        return quizzes.stream().anyMatch(x -> x.getId() == id);
    }

    public  void deleteQuiz(Quiz q){
        for (int i = 0 ; i < quizzes.size() ; i++){
        if (quizzes.get(i).getId() == q.getId()){
            System.out.println("Removing Index"+ i);
            quizzes.remove(q);
        }
        }
    }

    public void addCompletedQuiz(CompletedQuiz completedQuiz){
        completedQuizzes.add(completedQuiz);
    }

    public List<CompletedQuiz> getCompletedQuizzes() {
        return completedQuizzes;
    }

    public String getRoles() {
        return roles;
    }

    public String getPermissions() {
        return permissions;
    }

    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList(){
        if(this.permissions.length() > 0){
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Extract list of permissions (name)
        getPermissionList().forEach(p -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(p);
            authorities.add(authority);
        });

        // Extract list of roles (ROLE_name)
        getRoleList().forEach(r -> {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + r);
            authorities.add(authority);
        });

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return /*this.user.getActive() == 1;*/true;
    }


    @Override
    public String toString() {
        return "CustomUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles='" + roles + '\'' +
                ", permissions='" + permissions + '\'' +
                ", quizzes=" + quizzes +
                '}';
    }


    public Page<CompletedQuiz>/*List<Quiz>*/ getCompletedQuizzesPaged(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        PageImpl<CompletedQuiz> page=new PageImpl<CompletedQuiz>(getCompletedQuizzes(), paging, getCompletedQuizzes().size());
      //  Page<CompletedQuiz> pagedResult = getCompletedQuizzes();

        if(page.hasContent()) {
            return page;//.getContent();
        } else {
            return null ; //new ArrayList<Quiz>();
        }
    }



}
