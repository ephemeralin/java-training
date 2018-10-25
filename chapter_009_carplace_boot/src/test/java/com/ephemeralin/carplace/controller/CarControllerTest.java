package com.ephemeralin.carplace.controller;

import com.ephemeralin.carplace.model.Car;
import com.ephemeralin.carplace.model.Make;
import com.ephemeralin.carplace.service.*;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * The Car controller test.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CarController.class)
@AutoConfigureMockMvc(secure = false)
public class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private CarService carServiceMock;
    @MockBean
    private IModelService modelServiceMock;
    @MockBean
    private IBodyService bodyServiceMock;
    @MockBean
    private IEngineService engineServiceMock;
    @MockBean
    private IMakeService makeServiceMock;
    @MockBean
    private ITransmissionService transmissionServiceMock;
    @MockBean
    private IUserService userServiceMock;

    private Car car;
    private MockMultipartFile file;

    /**
     * Sets up car.
     */
    @Before
    public void setUpCar() {
        car = new Car();
        car.setId(1);
        Make make = new Make();
        make.setId(1);
        make.setName("Honda");
        car.setMake(make);
    }

    /**
     * Sets up file car photo.
     */
    @Before
    public void setUpFileCarPhoto() {
        file = new MockMultipartFile("file", "photo.jpg", "multipart/form-data", "some data" .getBytes());

    }

    /**
     * Test show all view.
     *
     * @throws Exception the exception
     */
    @Test
    public void testShowAllView() throws Exception {
        this.mockMvc.perform(get("/cars"))
                .andExpect(status().isOk())
                .andExpect(view().name("cars"))
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.view().name("cars"))
                .andExpect(content().string(Matchers.containsString("List - Car place")));
    }

    /**
     * Test add view.
     *
     * @throws Exception the exception
     */
    @Test
    public void testAddView() throws Exception {
        this.mockMvc.perform(get("/add_car")
                .param("filter_name", "All"))
                .andExpect(status().isOk())
                .andExpect(view().name("add_car"))
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.view().name("add_car"))
                .andExpect(content().string(Matchers.containsString("The car")));
    }

    /**
     * Test update view.
     *
     * @throws Exception the exception
     */
    @Test
    public void testUpdateView() throws Exception {
        assertNotNull(carServiceMock);
        when(carServiceMock.findById(1)).thenReturn(car);
        this.mockMvc.perform(get("/update_car")
                .param("car_id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("add_car"))
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.view().name("add_car"))
                .andExpect(content().string(Matchers.containsString("The car")));
    }

    /**
     * Test add car.
     *
     * @throws Exception the exception
     */
    @Test
    public void testAddCar() throws Exception {
        assertNotNull(carServiceMock);

        this.mockMvc.perform(
                multipart("/add_car")
                        .file(file)
                        .param("carId", "1")
                        .param("make", "2")
                        .param("model", "3")
                        .param("engine", "4")
                        .param("body", "5")
                        .param("transmission", "6")
                        .param("name", "Test Honda 1"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/cars"));
    }

    /**
     * Test delete car.
     *
     * @throws Exception the exception
     */
    @Test
    public void testDeleteCar() throws Exception {
        assertNotNull(carServiceMock);

        this.mockMvc.perform(
                post("/delete_car")
                        .param("car_id", "1"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/cars"));
    }
}
