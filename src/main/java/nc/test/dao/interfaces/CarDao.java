package nc.test.dao.interfaces;

import nc.test.model.Car;

import java.util.List;

public interface CarDao {
    List<Car> getFreeCars();
}
