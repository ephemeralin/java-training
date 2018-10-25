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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CarController.class)
@AutoConfigureMockMvc(secure = false)
//@SpringBootTest
public class CarControllerTest {
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

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    private Car car;
    @Before
    public void setUpCar() {
        car = new Car();
        car.setId(1);
        Make make = new Make(); make.setId(1); make.setName("Honda");
        car.setMake(make);
    }

    @Test
    public void testShowAllView() throws Exception {
        this.mockMvc.perform(get("/cars"))
                .andExpect(status().isOk())
                .andExpect(view().name("cars"))
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.view().name("cars"))
                .andExpect(content().string(Matchers.containsString("List - Car place")))
                .andDo(print());
    }

    @Test
    public void testAddView() throws Exception {
        this.mockMvc.perform(get("/add_car")
                .param("filter_name", "All"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("add_car"))
                    .andExpect(content().contentType("text/html;charset=UTF-8"))
                    .andExpect(MockMvcResultMatchers.view().name("add_car"))
                    .andExpect(content().string(Matchers.containsString("The car")))
                    .andDo(print());
    }

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
                .andExpect(content().string(Matchers.containsString("The car")))
                .andDo(print());
    }
}
