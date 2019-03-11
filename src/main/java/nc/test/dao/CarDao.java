package nc.test.dao;

import nc.test.model.Car;

import java.util.List;

public interface CarDao {
    List<Car> getFreeCars();
}
