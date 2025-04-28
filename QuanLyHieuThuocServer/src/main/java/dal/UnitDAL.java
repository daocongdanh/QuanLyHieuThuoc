package dal;

import entity.Unit;
import java.util.List;
import java.util.Optional;

public interface UnitDAL extends BaseDAL<Unit, String> {
    Unit findByName(String name);
    List<Unit> findByNameSearch(String name);
}
