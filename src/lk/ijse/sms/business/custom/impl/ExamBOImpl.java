/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.sms.business.custom.impl;

import java.util.ArrayList;
import lk.ijse.sms.business.custom.ExamBO;
import lk.ijse.sms.dao.DAOFactory;
import lk.ijse.sms.dao.custom.ExamDAO;
import lk.ijse.sms.db.HibernateUtil;
import lk.ijse.sms.dto.ExamDTO;
import lk.ijse.sms.entity.Course;
import lk.ijse.sms.entity.Exam;
import lk.ijse.sms.entity.Module;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Sahan Rajakaruna
 */
public class ExamBOImpl implements ExamBO {

    private ExamDAO examDAO;
    private SessionFactory sessionFactory;
    public ExamBOImpl() {
        this.examDAO = (ExamDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.exam);
        sessionFactory=HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean AddExam(ExamDTO exam) throws Exception {
        System.out.println("HERE");

        try (Session session = sessionFactory.openSession()) {
            examDAO.setSession(session);
            session.getTransaction().begin();
            examDAO.save(new Exam(new Module(exam.getModule_id()), new Course(exam.getCourse_id())));
            session.getTransaction().commit();
            return true;

        }catch (HibernateException ex){
            System.out.println("HERE"+ex);
            return false;
        }


    }

    @Override
    public boolean UpdateExam(ExamDTO exam) throws Exception {

//        return examDAO.update(new Exam(exam.getExam_id(), exam.getCourse_id(), exam.getModule_id()));
        return false;
    }

    @Override
    public boolean DeleteExam(String id) throws Exception {

//        return examDAO.delete(id);

        return false;

    }

    @Override
    public ExamDTO findById(String id) throws Exception {
        return null;
//        Exam examEnity = examDAO.findByID(id);
//        return new ExamDTO(examEnity.getExam_id(), examEnity.getCourse_id(), examEnity.getModule_id());
    }

    @Override
    public ArrayList<ExamDTO> allExam() throws Exception {
//
//        ArrayList<ExamDTO> examDTO = new ArrayList<>();
//        ArrayList<Exam> allexamEntity = examDAO.getAll();
//        for (Exam exam : allexamEntity) {
//            examDTO.add(new ExamDTO(exam.getExam_id(), exam.getCourse_id(), exam.getModule_id()));
//        }
//        return examDTO;
        return null;
    }

    @Override
    public boolean RemoveExam(String course_id, String module_code) throws Exception {

//        return examDAO.removeExam(course_id, module_code);
        return false;
    }

}
