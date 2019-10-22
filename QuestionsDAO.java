package com.virtusa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.virtusa.entities.Questions;

public class QuestionsDAO {
    Connection con;
    PreparedStatement pst;
    private static Logger logger=LogManager.getLogger(QuestionsDAO.class);
    public int generateQuestionId() {
        con=DaoConnection.getConnection();
        int question_id=0;
        String cmd="select case when  max(query_id) is NULL THEN 1 ELSE Max(query_id)+1 END qId from Queries";
        try {
            pst=con.prepareStatement(cmd);
            ResultSet rs=pst.executeQuery();
            while(rs.next()) {
            question_id=rs.getInt("qId");
            } 
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage());
        }
        return question_id;
    }
    public String addQuestionsDAO(Questions questions) {
        con=DaoConnection.getConnection();
        String cmd="insert into queries(query_id,query_name,query_description)values(?,?,?)";
        String result=" ";
        if(questions!=null) {
            try {
                int question_Id=generateQuestionId();
                pst=con.prepareStatement(cmd);
                pst.setInt(1,question_Id);
                pst.setString(2, questions.getQuestion_name());
                pst.setString(3, questions.getQuestion_description());         
                result="inserted successfully";
                pst.executeUpdate();
          }catch(Exception e) {
        	  String msg=e.getMessage();
              logger.error(msg);
              return msg;
                }
            }
        return result;
        }
    public Questions searchQuestionDao(int question_id) {
    
            con=DaoConnection.getConnection();
            String cmd="select * from queries where query_id=?";
            Questions questions=null;
            try {
                pst=con.prepareStatement(cmd);
                pst.setInt(1, question_id);
                ResultSet rs=pst.executeQuery();
                while(rs.next()) {
                    questions=new Questions();
                    questions.setQuestion_id(rs.getInt("query_id"));
                    questions.setQuestion_name(rs.getString("query_Name"));
                    questions.setQuestion_description(rs.getString("query_description"));
                    questions.setQuestion_status(rs.getString("query_status"));
                    questions.setQuestion_posted_date(rs.getDate("query_date"));   
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                logger.error(e.getMessage());
            }
            return questions;
        }
    
    public Questions searchQuestionDao(String question_name) {
        con=DaoConnection.getConnection();
        String cmd="select * from queries where query_name=?";
        Questions questions=null;
        try {
            pst=con.prepareStatement(cmd);
            pst.setString(1, question_name);
            ResultSet rs=pst.executeQuery();
            if(rs.next()) {
                questions=new Questions();
                questions.setQuestion_id(rs.getInt("query_id"));
                questions.setQuestion_name(rs.getString("query_Name"));
                questions.setQuestion_description(rs.getString("query_description"));
                questions.setQuestion_status(rs.getString("query_status"));
                questions.setQuestion_posted_date(rs.getDate("query_date"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage());
        }
        return questions;
      }
     public List<Questions> viewQuestion(String question_name) {
         con=DaoConnection.getConnection();
         String cmd="select * from queries where query_name=? AND query_status='active' ";
         List<Questions> questions=new ArrayList<Questions>();
         Questions qes=null;
         try {
             pst=con.prepareStatement(cmd);
             pst.setString(1, question_name);
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

}
   
