
package com.br.teste.ws.pessoa.main;

import com.br.teste.ws.service.PessoaService;
import javax.xml.ws.Endpoint;

/**
 *
 * @author vitcl
 */
public class Main {
    
    public static void main(String[] args) {
        PessoaService service = new PessoaService();
        Endpoint.publish("http://localhost:8080/coronaws", service);
        System.out.println("start in.. http://localhost:8080/coronaws");
    }
}
