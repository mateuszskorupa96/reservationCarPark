package pl.hycom.training.reservation.service.impl;

import java.util.List;

import org.apache.commons.lang3.NotImplementedException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import pl.hycom.training.reservation.api.service.IReservationService;
import pl.hycom.training.reservation.config.TestConfig;
import pl.hycom.training.reservation.dao.api.ICarParkDao;
import pl.hycom.training.reservation.exception.ParkingInvalidArgumentException;
import pl.hycom.training.reservation.exception.PlaceInvalidException;
import pl.hycom.training.reservation.exception.PlaceNotAvailableException;
import pl.hycom.training.reservation.model.LevelDTO;
import pl.hycom.training.reservation.model.ParkingDTO;

/**
 * JUnits for {@link ReservationServiceImpl} class implementation
 * 
 * @author <a href="mailto:dominik.klys@hycom.pl">Dominik Klys, HYCOM</a>
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
@Transactional
public class ReservationServiceImplTest {

    /**
     * Tested implementation class
     */
    @Autowired
    @Qualifier("carParkServiceDao")
    IReservationService reservationService;
    
    /**
     * DAO service initializes database
     */
    @Autowired
    @Qualifier("carParkDaoDB")
    ICarParkDao carParkDao;
    
    /**
     * Initializing method executed before each JUnit method
     */
    @Before
    public void init() {
        carParkDao.init();
    }
    
    /**
     * Method implements JUnit for {@link ReservationServiceImpl#getAllParkings()}
     */
    @Test
    public void testGetAllParkings() {
        List<ParkingDTO> list = reservationService.getAllParkings();
        Assert.assertNotNull(list);
        Assert.assertEquals(5, list.size());
    }
    
    /**
     * Method implements JUnit for {@link ReservationServiceImpl#findParking(int)}
     */
    @Test
    public void testFindParking() {
        ParkingDTO p = reservationService.findParking(5);
        Assert.assertNotNull(p);
        Assert.assertEquals(5, p.getId().intValue());
        Assert.assertFalse(p.getLevels().isEmpty());
    }
    
    /**
     * Method implements JUnit for {@link ReservationServiceImpl#findLevel(int, int)} - level found
     */
    @Test
    public void testFindLevel_OK() throws ParkingInvalidArgumentException {
        LevelDTO l = reservationService.findLevel(1, 1);
        Assert.assertNotNull(l);
        Assert.assertEquals(1, l.getId().intValue());
    }
    
    /**
     * Method implements JUnit for {@link ReservationServiceImpl#findLevel(int, int)} - level NOT found
     */
    @Test(expected = ParkingInvalidArgumentException.class)
    public void testFindLevel_Exception() throws ParkingInvalidArgumentException {
        reservationService.findLevel(2, 1);
    }
    
    @Test
    public void testBook_OK() throws PlaceInvalidException, PlaceNotAvailableException {
        throw new NotImplementedException("Not implemented yet");
    }
    
    @Test(expected = PlaceInvalidException.class)
    public void testBook_PlaceInvalidException() throws PlaceInvalidException, PlaceNotAvailableException {
        throw new NotImplementedException("Not implemented yet");
    }
    
    @Test(expected = PlaceNotAvailableException.class)
    public void testBook_PlaceNotAvailableException() throws PlaceNotAvailableException, PlaceInvalidException {
        throw new NotImplementedException("Not implemented yet");
    }
    
    @Test
    public void testRelease_OK() throws PlaceInvalidException {
        throw new NotImplementedException("Not implemented yet");
    }

    @Test(expected = PlaceInvalidException.class)
    public void testRelease_PlaceInvalidException() throws PlaceInvalidException {
        throw new NotImplementedException("Not implemented yet");
    }
}
