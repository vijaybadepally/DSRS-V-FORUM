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

public class AnswersDAO {
	Connection con;
    PreparedStatement pst;
    private static Logger logger=LogManager.getLogger(AnswersDAO.class);
    public Answers viewAnswersDao(int question_id) {
        con=DaoConnection.getConnection();
        String cmd="select * from Answers where query_id=?";
        Answers answer=null;
        try {
            pst=con.prepareStatement(cmd);
            pst.setInt(1, question_id);
            ResultSet rs=pst.executeQuery();
            while(rs.next()) {
                answer=new Answers();
                answer.setQuestion_id(rs.getInt("query_id"));
                answer.setAnswer_id(rs.getInt("answer_id"));
                answer.setAnswer_name(rs.getString("answer_name"));
                answer.setAnswer_description(rs.getString("answer_description"));
                answer.setStatus(rs.getString("answer_status"));
                answer.setAns_posted_date(rs.getDate("posted_date"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage());
        }
        return answer;
    }
    public Answers adminViewAnswers(int answer_id) {
        con=DaoConnection.getConnection();
        String cmd="select * from Answers where answer_id=?";
        Answers answer=null;
        try {
            pst=con.prepareStatement(cmd);
            pst.setInt(1, answer_id);
            ResultSet rs=pst.executeQuery();
            while(rs.next()) {
                answer=new Answers();
                answer.setQuestion_id(rs.getInt("query_id"));
                answer.setAnswer_id(rs.getInt("answer_id"));
                answer.setAnswer_name(rs.getString("answer_name"));
                answer.setAnswer_description(rs.getString("answer_description"));
                answer.setStatus(rs.getString("answer_status"));
                answer.setAns_posted_date(rs.getDate("posted_date"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage());
        }
        return answer;
    }
    public String postAnswersDao(int question_id,Answers answer) {
            con=DaoConnection.getConnection();
            String result=" ";
            Questions qes=new QuestionsDAO().searchQuestionDao(question_id);
            String status=qes.getQuestion_status();
            if(answer!=null && status.equalsIgnoreCase("active")) {
            try {
                String cmd="insert into answers(Query_id,answer_id,answer_name,answer_description) values(?,?,?,?)";

                pst=con.prepareStatement(cmd);
                pst.setInt(1, question_id);
                pst.setInt(2, answer.getAnswer_id());
                pst.setString(3, answer.getAnswer_name());
                pst.setString(4, answer.getAnswer_description());
                pst.executeUpdate();
                result="inserted sucessfully";
            } catch (SQLException e) {
                // TODO Auto-generated catch block
            	String msg=e.getMessage();
                logger.error(msg);
                return msg;
            }
            }else {
                result="cannot insert";
            }
            return result;
        }
    public int generateAnswerId() {
        con=DaoConnection.getConnection();
        int answer_id=0;
        String cmd="select case when  max(answer_id) is NULL THEN 1 ELSE Max(answer_id)+1 END ansId from Answers";
        try {
            pst=con.prepareStatement(cmd);
            ResultSet rs=pst.executeQuery();
           while( rs.next()) {
            answer_id=rs.getInt("ansId");
           }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return answer_id;
    }
     public List<Answers> viewAnswers(int question_id) {
            con=DaoConnection.getConnection();
            String cmd="select * from Answers where query_id=? and answer_status='active'";
            List<Answers> answers=new ArrayList<Answers>();
            Answers answer=null;
            try {
                pst=con.prepareStatement(cmd);
                pst.setInt(1, question_id);
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
}
