package br.com.ucsal.controller;



import java.io.IOException;

import br.com.ucsal.persistencia.HSQLProdutoRepository;
import br.com.ucsal.service.ProdutoService;
import br.com.ucsal.util.command.Rota;
import br.com.ucsal.util.ioc.Inject;
import br.com.ucsal.util.ioc.Injectable;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Injectable
@Rota("/adicionarProduto")
public class ProdutoAdicionarServlet implements Command {
 private static final long serialVersionUID = 1L;


 private ProdutoService produtoService;

 @Inject
 public ProdutoAdicionarServlet(ProdutoService produtoService) {
    this.produtoService = produtoService;
 }

 @Override
 public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     String method = request.getMethod();
     
     if ("GET".equalsIgnoreCase(method)) {
         doGet(request, response);
     } else if ("POST".equalsIgnoreCase(method)) {
         doPost(request, response);
     }
 }

 private void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/produtoformulario.jsp");
     dispatcher.forward(request, response);
 }

 private void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     String nome = request.getParameter("nome");
     double preco = Double.parseDouble(request.getParameter("preco"));
     produtoService.adicionarProduto(nome, preco);
     response.sendRedirect("listarProdutos");
 }

}

