package nc.test.dao;

import nc.test.model.Car;

import java.util.List;

public interface CarDao {

    List<Car> getFreeCars();

    List<Car> getAllCars();

    void insert(Car car);

    boolean carIsEmpty(String number);
}
