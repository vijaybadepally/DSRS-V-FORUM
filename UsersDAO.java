package com.virtusa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.virtusa.entities.Answers;
import com.virtusa.entities.Questions;
import com.virtusa.entities.Users;

public class UsersDAO {
	Connection con;
    PreparedStatement pst;
    private static Logger logger=LogManager.getLogger(UsersDAO.class);
    public String addUsersDao(Users users) {
            con=DaoConnection.getConnection();
            String result=" ";
            int id=users.getUser_id();
            Users u=new UsersDAO().searchUsersDao(id);
            if(u==null) {
                if(id!=0){
                    try {
                        String cmd="insert into Users(user_id,user_name,date_of_birth,mail_id,employee_type)values(?,?,?,?,?)";
                        pst=con.prepareStatement(cmd);
                        pst.setInt(1, id);   
                        pst.setString(2, users.getUsername());
                        java.sql.Date sqlDate = new java.sql.Date(users.getDate_of_birth().getTime());
                        pst.setDate(3, sqlDate);
                        pst.setString(4, users.getEmail_id());
                        pst.setString(5,users.getEmployee_type());
                        pst.executeUpdate();
                        result="user inserted successfully";
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                    	String msg=e.getMessage();
                        logger.error(msg);
                        return msg;
                    }
                }else {
                    result="cannot be created..";
                }
            }else {
                result="user_id already exsits";
            }
            return result;
        }
    public Users searchUsersDao(int user_id) {
        con=DaoConnection.getConnection();
        String cmd="select * from Users where User_id=?";
        Users users=null;
        try {
            pst=con.prepareStatement(cmd);
            pst.setInt(1, user_id);
            ResultSet rs=pst.executeQuery();
            if(rs.next()) {
                users=new Users();
                users.setUser_id(rs.getInt("user_id"));
                users.setUsername(rs.getString("user_name"));
                users.setDate_of_birth(rs.getDate("date_of_birth"));
                users.setPassword(rs.getString("password"));
                users.setEmail_id(rs.getString("mail_id"));
                users.setEmployee_type(rs.getString("employee_type"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage());
        }
    return users;
    }
    public Users searchLoginDao(int user_id, String password,String employee_type) {
        con=DaoConnection.getConnection();
        String cmd="select * from Users where User_id=? and password=? and employee_type=?";
        Users users=null;
        try {
            pst=con.prepareStatement(cmd);
            pst.setInt(1, user_id);
            pst.setString(2, password);
            pst.setString(3,employee_type);
            ResultSet rs=pst.executeQuery();
            if(rs.next()) {
                users=new Users();
                users.setUser_id(rs.getInt("user_id"));
                users.setUsername(rs.getString("user_name"));
                users.setDate_of_birth(rs.getDate("date_of_birth"));
                users.setPassword(rs.getString("password"));
                users.setEmail_id(rs.getString("mail_id"));
                users.setEmployee_type(rs.getString("employee_type"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage());
        }
    return users;
    }
    public String updatePassword(int user_id,String pwd) {
        String res="";
        con=DaoConnection.getConnection();
        String cmd="update Users set Password=? where user_id=?";
        Users users=new UsersDAO().searchUsersDao(user_id);
        if(users!=null) {
            try {
                pst=con.prepareStatement(cmd);
                pst.setString(1, pwd);
                pst.setInt(2, user_id);
                pst.executeUpdate();
                res="password Updated";
            } catch (SQLException e) {
                // TODO Auto-generated catch block
            	String msg=e.getMessage();
                logger.error(msg);
                return msg;
            }
        }
        return res;
    }
    public String deleteUsers(int user_id) {
    	con=DaoConnection.getConnection();
        Users users=new UsersDAO().searchUsersDao(user_id);
        int id=0;
        if(users!=null) {
         id=users.getUser_id();
        }
        String res="";
        if(id!=0) {
            try {
                  String cmd="delete from Users where user_id=?";
                pst=con.prepareStatement(cmd);
                pst.setInt(1, id);
                pst.executeUpdate();
                res="user deleted";
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();
                
                String msg=e.getMessage();
                logger.error(msg);
                return msg;
            }   
        }else {
                res="user doesnot exist..";
            }
        return res;
    }
    public List<Questions> viewQuestions() {
           con=DaoConnection.getConnection();
           String cmd="select * from queries ";
           List<Questions> questions=new ArrayList<Questions>();
           Questions qes=null;
           try {
               pst=con.prepareStatement(cmd);
               ResultSet rs=pst.executeQuery();
               while(rs.next()) {
                  qes=new Questions();
                   qes.setQuestion_id(rs.getInt("query_id"));
                   qes.setQuestion_name(rs.getString("query_Name"));
                   qes.setQuestion_description(rs.getString("query_description"));
                   qes.setQuestion_status(rs.getString("query_status"));
                   qes.setQuestion_posted_date(rs.getDate("query_date"));
                   questions.add(qes);
               }
           } catch (SQLException e) {
               logger.error(e.getMessage());
           }
           return questions;
       }
       public String updateQuestionDao(int question_id ) {
           con=DaoConnection.getConnection();
           String cmd="Update Queries SET query_status=? where query_id=? " ;
           String res="";
           Questions questions=new QuestionsDAO().searchQuestionDao(question_id);
           if(questions!=null) {
               try {
                   pst=con.prepareStatement(cmd);
                   pst.setString(1, "active");
                   pst.setInt(2, question_id);
                   pst.executeUpdate();
                   res="Question_status Updated";
               } catch (SQLException e) {
                   // TODO Auto-generated catch block
            	   String msg=e.getMessage();
                   logger.error(msg);
                   return msg;
               }
               }else {
                   res="question not found..";
               }
           return res;
       }
        public List<Answers> viewAnswers() {
               con=DaoConnection.getConnection();
               String cmd="select * from Answers";
               List<Answers> answers=new ArrayList<Answers>();
               Answers answer=null;
               try {
                   pst=con.prepareStatement(cmd);
                   ResultSet rs=pst.executeQuery();
                   while(rs.next()) {
                       answer=new Answers();
                       answer.setQuestion_id(rs.getInt("query_id"));
                       answer.setAnswer_id(rs.getInt("answer_id"));
                       answer.setAnswer_name(rs.getString("answer_name"));
                       answer.setAnswer_description(rs.getString("answer_description"));
                       answer.setStatus(rs.getString("answer_status"));
                       answer.setAns_posted_date(rs.getDate("posted_date"));
                       answers.add(answer);
                   }
               } catch (SQLException e) {
                   // TODO Auto-generated catch block
                   logger.error(e.getMessage());
               }
               return answers;
        }
       public String updateAnswerDao(int answer_id ) {
           con=DaoConnection.getConnection();
           String cmd="update Answers set answer_status='active' where answer_id=?";
           String res="";
           Answers answers=new AnswersDAO().adminViewAnswers(answer_id);
           if(answers!=null) {
               try {
                   pst=con.prepareStatement(cmd);
                   pst.setInt(1, answer_id);
                   pst.executeUpdate();
                   res="answer updated....";
               } catch (SQLException e) {
                   // TODO Auto-generated catch block
            	   String msg=e.getMessage();
                   logger.error(msg);
                   return msg;
               }
               }else {
                   res="answer not found..";
               }
           return res;
       }
}
  
