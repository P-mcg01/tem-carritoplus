package com.emergentes.carritoplus;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String op = request.getParameter("op");
    
    if(op.equals("vaciar")) {
      HttpSession ses = request.getSession();

      ses.invalidate();
      response.sendRedirect("index.jsp");      
    }
    
    if(op.equals("eliminar")) {
      int id = Integer.parseInt(request.getParameter("id"));
      int pos = -1;
      int buscado = -1;
      
      HttpSession ses = request.getSession();
      ArrayList<Producto> lista = (ArrayList<Producto>)ses.getAttribute("almacen");
      
      for(Producto p : lista) {
        pos++;
        if(p.getId() == id) {
          buscado = pos;          
        }
      }
      lista.remove(buscado);
      response.sendRedirect("index.jsp");
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    String producto = request.getParameter("producto");
    int cantidad = Integer.parseInt(request.getParameter("cantidad"));
    double precio = Double.parseDouble(request.getParameter("precio"));
    
    Producto prod = new Producto();
    
    prod.setId(id);
    prod.setProducto(producto);
    prod.setCantidad(cantidad);
    prod.setPrecio(precio);
    
    HttpSession ses = request.getSession();
    ArrayList<Producto> lista = (ArrayList<Producto>)ses.getAttribute("almacen");
    lista.add(prod);
    
    response.sendRedirect("index.jsp");
  }  
}
