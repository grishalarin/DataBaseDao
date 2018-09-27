package Manufacturer;

import Pojo.Manufacturer;

public interface ManufacturerDao {
    public boolean addManufacturer(Manufacturer manufacturer);

    public Manufacturer getManufacturerById(Integer id);

    public boolean updateManufacterById(Manufacturer manufacturer);

    public boolean deleteManufacturerById(Integer id);
}
