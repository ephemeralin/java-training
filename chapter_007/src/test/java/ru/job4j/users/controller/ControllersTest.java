package ru.job4j.users.controller;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ru.job4j.users.model.User;
import ru.job4j.users.model.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ControllersTest {

    @Test
    public void a_createUser() throws IOException, ServletException {
        final CreateController controller = new CreateController();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        String userEmail = "root_test@google.com";
        when(request.getParameter("login")).thenReturn("root_test");
        when(request.getParameter("email")).thenReturn(userEmail);
        when(request.getParameter("name")).thenReturn("root_test");
        when(request.getParameter("password")).thenReturn("root_test");
        when(request.getParameter("role")).thenReturn("admin");

        controller.doPost(request, response);
        final User user = UserStore.getInstance().getByEmail(userEmail);

        assertThat(user.getLogin(), is("root_test"));
    }

    @Test
    public void b_updateUser() throws IOException, ServletException {
        final UpdateController controller = new UpdateController();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        String userEmail = "root_test@google.com";
        when(request.getParameter("login")).thenReturn("root_test_changed");
        when(request.getParameter("email")).thenReturn(userEmail);
        when(request.getParameter("name")).thenReturn("root_test");
        when(request.getParameter("password")).thenReturn("root_test");
        when(request.getParameter("role")).thenReturn("admin");

        controller.doPost(request, response);
        final User user = UserStore.getInstance().getByEmail(userEmail);

        assertThat(user.getLogin(), is("root_test_changed"));
    }

    @Test
    public void c_deleteUser() throws IOException, ServletException {
        final DeleteController controller = new DeleteController();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        String userEmail = "root_test@google.com";
        when(request.getParameter("email")).thenReturn(userEmail);

        controller.doPost(request, response);
        final User user = UserStore.getInstance().getByEmail(userEmail);

        assertNull(user);
    }

}