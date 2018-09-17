package lk.ijse.sms.db;

import lk.ijse.sms.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;

public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();

    public static SessionFactory buildSessionFactory(){
        File hibernateFile = new File("resource/application.properties");
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .loadProperties(hibernateFile)
                .build();
        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Batch.class)
                .addAnnotatedClass(StudentBatch.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Module.class)
                .addAnnotatedClass(CourseModule.class)
                .addAnnotatedClass(Exam.class)
                .addAnnotatedClass(StudentExam.class)
                .buildMetadata();
        return metadata.getSessionFactoryBuilder().build();

    }
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

}
