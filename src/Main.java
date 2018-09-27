import Dao.MobileDao;
import Dao.MobileDaoJdbcImpl;
import Manufacturer.ManufacturerDao;
import Manufacturer.ManufacturerDaoJdbcImps;
import Pojo.Manufacturer;
import Pojo.Mobile;

public class Main {
    public static void main(String[] args) {

        Manufacturer manufacturer = new Manufacturer(1, "Iphone6s", "USA");
        Mobile mobile = new Mobile(1, "Iphone 2", 25000F, manufacturer);
        MobileDao mobileDao = new MobileDaoJdbcImpl();
        ManufacturerDao manufacturerDao = new ManufacturerDaoJdbcImps();

        // mobileDao.addMobile(mobile);
        // mobileDao.deleteMobileById(8);
        mobileDao.getMobileById(1);
        mobile.setPrice(80000F);
        mobileDao.updateMobileById(mobile);
        mobileDao.getMobileById(4);

        //manufacturerDao.addManufacturer(manufacturer);
        //manufacturerDao.deleteManufacturerById(4);
        manufacturerDao.getManufacturerById(4);
        manufacturer.setCountry("Brazil");
        manufacturerDao.updateManufacterById(manufacturer);
        manufacturerDao.getManufacturerById(4);

    }
}
