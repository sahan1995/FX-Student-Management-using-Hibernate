/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.sms.business.custom.impl;

import java.util.ArrayList;
import java.util.List;

import lk.ijse.sms.business.custom.CourseModuleBO;
import lk.ijse.sms.dao.DAOFactory;
import lk.ijse.sms.dao.custom.CourseModuleDAO;
import lk.ijse.sms.db.HibernateUtil;
import lk.ijse.sms.dto.CourseModuleDTO;
import lk.ijse.sms.entity.CourseModule;
import lk.ijse.sms.entity.CourseModule_PK;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Sahan Rajakaruna
 */
public class CourseModuleBOImpl implements CourseModuleBO {

    private CourseModuleDAO courseModuleDAO;
    private SessionFactory sessionFactory;
    public CourseModuleBOImpl() {
        this.courseModuleDAO = (CourseModuleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.course_module);
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean AddCourseModule(CourseModuleDTO courseModule) throws Exception {
        System.out.println("HERE courseModule");
        try (Session session = sessionFactory.openSession()) {
            courseModuleDAO.setSession(session);
            session.beginTransaction();

            System.out.println(courseModule);
            courseModuleDAO.save(new CourseModule(courseModule.getCourse_id(),courseModule.getModule_id()));

            session.getTransaction().commit();

            return true;
        }catch (HibernateException ex){
            System.out.println("HERE"+ex);
            return false;
        }


    }

    @Override
    public boolean UpdateCourseModule(CourseModuleDTO courseModule) throws Exception {

        try (Session session = sessionFactory.openSession()) {
            courseModuleDAO.setSession(session);
            session.beginTransaction();

            courseModuleDAO.update(new CourseModule(courseModule.getCourse_id(),courseModule.getModule_id()));

            session.getTransaction().commit();

            return true;
        }catch (HibernateException ex){
            return false;
        }
    }

    @Override
    public boolean DeleteCourseModule(String course_id, String module_id) throws Exception {

        try (Session session = sessionFactory.openSession()) {
            courseModuleDAO.setSession(session);
            session.beginTransaction();

            courseModuleDAO.delete(new CourseModule_PK(course_id,module_id));

            session.getTransaction().commit();

            return true;
        }catch (HibernateException ex){
            return false;
        }
    }

    @Override
    public CourseModuleDTO findById(String course_id, String module_id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            courseModuleDAO.setSession(session);
            session.beginTransaction();

            CourseModule courseModule = courseModuleDAO.findByID(new CourseModule_PK(course_id, module_id));
            session.getTransaction().commit();

            return new CourseModuleDTO(courseModule.getCourseModule_PK().getCourse_id(),courseModule.getCourseModule_PK().getModule_id());
        }catch (HibernateException ex){
            return null;
        }

    }

    @Override
    public ArrayList<CourseModuleDTO> allCourseModules() throws Exception {
        ArrayList<CourseModuleDTO> allCourseModuleDTO = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            courseModuleDAO.setSession(session);
            session.beginTransaction();

            List<CourseModule> allCourseModulesEntity = courseModuleDAO.getAll();
            session.getTransaction().commit();
            for (CourseModule courseModule : allCourseModulesEntity) {
                allCourseModuleDTO.add(new CourseModuleDTO(courseModule.getCourseModule_PK().getCourse_id(), courseModule.getCourseModule_PK().getModule_id()));
            }
            return allCourseModuleDTO;
        }catch (HibernateException ex){
            return null;
        }
    }

    @Override
    public boolean addCourseModule(String courseid, String... moduleId) throws Exception {
//        return courseModuleDAO.addCourseModule(courseid, moduleId);
        return false;
    }

}
