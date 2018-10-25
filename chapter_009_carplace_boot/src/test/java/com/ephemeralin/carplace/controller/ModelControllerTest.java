package com.ephemeralin.carplace.controller;

import com.ephemeralin.carplace.model.Make;
import com.ephemeralin.carplace.model.Model;
import com.ephemeralin.carplace.service.IMakeService;
import com.ephemeralin.carplace.service.IModelService;
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
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * The Model controller test.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ModelController.class)
@AutoConfigureMockMvc(secure = false)
public class ModelControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private IModelService modelServiceMock;
    @MockBean
    private IMakeService makeServiceMock;

    private Make make;
    private List<Model> modelList;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        make = new Make(); make.setId(1); make.setName("Honda");

        Model firstModel = new Model();
        firstModel.setId(1);
        firstModel.setName("Accord");
        firstModel.setMake(make);

        Model secondModel = new Model();
        secondModel.setId(2);
        secondModel.setName("CR-V");
        secondModel.setMake(make);

        modelList = new ArrayList<>();
        modelList.add(firstModel);
        modelList.add(secondModel);
    }

    /**
     * Test get models view.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetModelsView() throws Exception {
        when(makeServiceMock.findById(1)).thenReturn(make);
        when(modelServiceMock.findByMake(make)).thenReturn(modelList);
        this.mockMvc.perform(post("/models").param("make_id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string(Matchers.containsString("[{\"id\":1,\"name\":\"Accord\",\"make\":{\"id\":1,\"name\":\"Honda\"}},{\"id\":2,\"name\":\"CR-V\",\"make\":{\"id\":1,\"name\":\"Honda\"}}]")))
                .andDo(print());
    }
}
