package hiber.service;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      long id_car = user.getCar().getId();
      List<User> isUser = sessionFactory.getCurrentSession().createQuery("from User where car.id = :id_car" )
              .setParameter("id_car", id_car)
              .getResultList();
      if (isUser.size() > 0) System.out.println("Эта машина занята");
      else sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUser(String model, int serial) {
      TypedQuery<Car> finCar = sessionFactory.getCurrentSession()
              .createQuery("from Car where model = :model and  serial = :serial")
              .setParameter("model", model)
              .setParameter("serial", serial);
      Car car = finCar.getResultList().get(0);
      List<User> result = listUsers();
      if (result.size() != 0) {
         User user = result.stream()
                 .filter(u -> u.getCar().equals(car))
                 .findFirst()
                 .orElse(null);
         return user ;
      }else return null ;
   }

}
